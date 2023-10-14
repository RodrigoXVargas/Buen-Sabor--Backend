package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.OrderMapper;
import com.project.buensabor.dto.orderDto.MovementDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderWithoutuserDto;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.dto.productDto.ProductDtos.ProductDto;
import com.project.buensabor.entities.*;
import com.project.buensabor.enums.StatusType;
import com.project.buensabor.enums.TypeMovement;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.repositories.*;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.MovementService;
import com.project.buensabor.services.interfaces.OrderService;
import com.project.buensabor.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class OrderServiceImpl extends BaseServicesDTOImpl<Order, OrderDto, OrderMapper, Long> implements OrderService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private StatusRepository statusRepository;


    public OrderServiceImpl(BaseRepository<Order, Long> baseRepository, OrderMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ProductService productService;


    @Autowired
    private DateService dateService;

    @Override
    @Transactional
    public List<OrderWithoutuserDto> ordersByUserId(Long id) throws CustomException {
        try {
            List<Order> entities = orderRepository.findOrdersByUserId(id);
            List<OrderWithoutuserDto> entitiesDtos = new ArrayList<>();
            if (!entities.isEmpty()){
                for (Order entity: entities) {
                    OrderWithoutuserDto orderWithoutuserDto = modelMapper.map(entity, OrderWithoutuserDto.class);
                    orderWithoutuserDto.setProducts(this.getOrderProductsByOrder(orderWithoutuserDto.getId()));
                    entitiesDtos.add(orderWithoutuserDto);
                }
            }
            return entitiesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<OrderDto> getOrdersByStatus(Long id) throws CustomException {
        try {
            List<Order> entities = orderRepository.findOrdersByStatusOrderId(id);
            List<OrderDto> entitiesDtos = new ArrayList<>();
            if (!entities.isEmpty()){
                for (Order entity: entities) {
                    OrderDto orderDto = mapper.convertToDto(entity);
                    orderDto.setProducts(getOrderProductsByOrder(orderDto.getId()));
                    entitiesDtos.add(orderDto);
                }
            }
            return entitiesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public String changeStatus(StatusOrderDto status, Long id) throws CustomException {
        try{
            Optional<Order> orderOptional = orderRepository.findById(id);
            Order order = orderOptional.get();
            StatusOrderDto statusPrevious = modelMapper.map(order.getStatusOrder(), StatusOrderDto.class);
            order.setStatusOrder(modelMapper.map(status, StatusOrder.class));
            order = orderRepository.save(order);
            List<OProductsWithoutOrderDto> oProductsWithoutOrderDtoList = this.getOrderProductsByOrder(order.getId());

            if(statusPrevious.getStatusType().equals(StatusType.In_Queue)) {
                if (status.getStatusType().equals(StatusType.In_Preparation) || status.getStatusType().equals(StatusType.Ready)){
                    productService.descontarOReponerStock(oProductsWithoutOrderDtoList, false);
                }
            } else {
                if (status.getStatusType().equals(StatusType.Cancelled)){
                    productService.descontarOReponerStock(oProductsWithoutOrderDtoList, true);
                }
            }

            this.notificarTopicos(statusPrevious);
            this.notificarTopicos(status);
            this.notificarUsuario(order.getUser());

            return "Se cambio el status a "+ order.getStatusOrder().getStatusType().name();
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Transactional
    public String cancelOrder(Long id, String description) throws CustomException {
        try{
            Optional<Order> orderOptional = orderRepository.findById(id);
            Order order = orderOptional.get();
            Optional<StatusOrder> optional = statusRepository.findByStatusType(StatusType.Cancelled.name());
            StatusOrder statusOrder = optional.get();
            StatusOrder statusPrevious = order.getStatusOrder();
            order.setStatusOrder(statusOrder);
            order = orderRepository.save(order);
            List<OProductsWithoutOrderDto> oProductsWithoutOrderDtoList = this.getOrderProductsByOrder(order.getId());

            productService.descontarOReponerStock(oProductsWithoutOrderDtoList, true);

            Movement movement = new Movement(
                    TypeMovement.Credit_Note,
                    dateService.dateNow(),
                    description,
                    order.getTotalPrice()*-1,
                    order);
            movement = movementRepository.save(movement);

            this.notificarTopicos(modelMapper.map(statusPrevious, StatusOrderDto.class));
            this.notificarTopicos(modelMapper.map(statusOrder, StatusOrderDto.class));
            this.notificarUsuario(order.getUser());

            return "Se cambio el status a "+ order.getStatusOrder().getStatusType().name();
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public String plusMinutesOrder(Long idOrder, Long minutes) throws CustomException {
        try{
            Optional<Order> orderOptional = orderRepository.findById(idOrder);
            Order order = orderOptional.get();
            order.setTotalCookingTime(order.getTotalCookingTime()+minutes);
            order = orderRepository.save(order);

            return "Se añadió "+ minutes + " a la orden "+ idOrder;
        } catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<OrderWithoutuserDto> getOrdersByIdUserBetweenDates(Long id, LocalDate desde, LocalDate hasta) throws CustomException {
        try{
            if (id==0){
                throw new CustomException("El id no puede ser 0 o nulo");
            }
            List<Order> orderList = orderRepository.findOrdersByUserIdBetweenDates(id, desde, hasta);
            List<OrderWithoutuserDto> orderWithoutuserDtoList = new ArrayList<>();
            for (Order order: orderList) {
                OrderWithoutuserDto orderWithoutuserDto = modelMapper.map(order, OrderWithoutuserDto.class);
                orderWithoutuserDto.setProducts(this.getOrderProductsByOrder(orderWithoutuserDto.getId()));
                orderWithoutuserDtoList.add(orderWithoutuserDto);
            }

            return orderWithoutuserDtoList;
        } catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public void notificarUsuario(User user) throws CustomException {
        try {
            List<StatusType> statusTypes = Arrays.asList(
                    StatusType.In_Queue,
                    StatusType.In_Preparation,
                    StatusType.Ready,
                    StatusType.Out_for_Delivery);
            List<OrderWithoutuserDto> orderDtoList = this.ordersByUserId(user.getId());
            List<OrderWithoutuserDto> orderFilters = new ArrayList<>();
            for (OrderWithoutuserDto order : orderDtoList) {
                if (statusTypes.contains(order.getStatusOrder().getStatusType())) {
                    orderFilters.add(order);
                }
            }
            messagingTemplate.convertAndSendToUser(user.getMail(),"/private", orderFilters);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public void notificarTopicos(StatusOrderDto status) throws CustomException {
        try{
            List<OrderDto> orderDtoList;
            if (status.getStatusType() == StatusType.Ready || status.getStatusType() == StatusType.In_Queue){
                orderDtoList = this.getOrdersByStatus(1l);
                List<OrderDto> orderDtoList2 = this.getOrdersByStatus(3l);
                if (orderDtoList2.size() != 0) {
                    for (OrderDto orderDto: orderDtoList2) {
                        orderDtoList.add(orderDto);
                    }
                }

                messagingTemplate.convertAndSend("/topic/cashiers", orderDtoList);
                System.out.println("notificacion cajeros");
            } else if (status.getStatusType() == StatusType.In_Preparation) {
                orderDtoList = this.getOrdersByStatus(2l);
                messagingTemplate.convertAndSend( "/topic/chefs", orderDtoList);
                System.out.println("notificacion chefs");
            } else if (status.getStatusType() == StatusType.Out_for_Delivery) {
                orderDtoList = this.getOrdersByStatus(4l);
                messagingTemplate.convertAndSend( "/topic/deliveries", orderDtoList);
                System.out.println("notificacion deliveries");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional //Indica que el método es una transacción.
    public List<OrderDto> findAll() throws CustomException {
        try {
            List<Order> entities = baseRepository.findAll();
            List<OrderDto> entitiesDtos = new ArrayList<>();
            for (Order entity : entities) {
                OrderDto orderDto = mapper.convertToDto(entity);
                orderDto.setProducts(getOrderProductsByOrder(orderDto.getId()));
                entitiesDtos.add(orderDto);
            }
            return entitiesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }



    @Override
    @Transactional
    public OrderDto findById(Long id) throws CustomException {
        try {
            Optional<Order> entityOptional = orderRepository.findById(id);
            Order order = entityOptional.get();
            OrderDto orderDto = mapper.convertToDto(order);
            orderDto.setProducts(getOrderProductsByOrder(orderDto.getId()));
            return orderDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public List<OProductsWithoutOrderDto> getOrderProductsByOrder(Long idOrder) throws CustomException {
        try{
            List<OrderProducts> orderProductsList = orderProductsRepository.findOrderProductsByOrderId(idOrder);
            List<OProductsWithoutOrderDto> oProductsWithoutOrderDtos = new ArrayList<>();
            for (OrderProducts orderProducts: orderProductsList) {
                OProductsWithoutOrderDto withoutOrderDto = modelMapper.map( orderProducts, OProductsWithoutOrderDto.class);
                oProductsWithoutOrderDtos.add(withoutOrderDto);
            }
            return oProductsWithoutOrderDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }



    @Override
    @Transactional
    public OrderDto saveOne(OrderDto entityDto) throws CustomException {
        try {

            if (!productService.validarStock(entityDto.getProducts())){
                throw new CustomException("No hay stock para cumplir con el pedido completo");
            }

            Order entity = new Order();
            modelMapper.map(entityDto, entity);
            entity.setCreationDate(dateService.dateNow());
            entity.setTotalCookingTime(getTotalCookingTime(entityDto));
            Optional<User> user = userRepository.findById(entityDto.getUser().getId());
            entity.setUser(user.get());
            entity = orderRepository.save(entity);

            List<OProductsWithoutOrderDto> withoutOrderDtoList = new ArrayList<>();
            double totalOrder =0;
            if (entityDto.getProducts().size()!=0) {
                for (OProductsWithoutOrderDto oProductsWithoutOrderDto : entityDto.getProducts()) {
                    Optional<Product> optionalProduct = productRepository.findById(oProductsWithoutOrderDto.getProduct().getId());
                    Product product = optionalProduct.get();
                    OrderProducts orderProducts = new OrderProducts(
                            entity,
                            product,
                            oProductsWithoutOrderDto.getCant()
                    );
                    orderProducts = orderProductsRepository.save(orderProducts);
                    oProductsWithoutOrderDto = modelMapper.map(orderProducts, OProductsWithoutOrderDto.class);
                    withoutOrderDtoList.add(oProductsWithoutOrderDto);
                    totalOrder = product.getPrice()*oProductsWithoutOrderDto.getCant();
                }
            }
            entity.setTotalPrice(totalOrder);
            entity = orderRepository.save(entity);

            entityDto = mapper.convertToDto(entity);
            entityDto.setProducts(withoutOrderDtoList);

            Movement movement = new Movement(
                    TypeMovement.Bill,
                    dateService.dateNow(),
                    "Order: "+entity.getId(),
                    entity.getTotalPrice(),
                    entity);
            movement = movementRepository.save(movement);

            List<OrderDto> orderDtoList = this.getOrdersByStatus(1l);
            List<OrderDto> orderDtoList2 = this.getOrdersByStatus(3l);
            if (orderDtoList2.size() != 0) {
                for (OrderDto orderDto: orderDtoList2) {
                    orderDtoList.add(orderDto);
                }
            }
            //System.out.println("notificacion cajero");
            messagingTemplate.convertAndSend("/topic/cashiers", orderDtoList);

            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public OrderDto updateOne(OrderDto entityDto, Long id) throws CustomException {
        try {
            Optional<Order> entityOptional = orderRepository.findById(id);
            Order entityUpdate = entityOptional.get();
            modelMapper.map(entityDto, entityUpdate);
            entityUpdate = orderRepository.save(entityUpdate);

            List<OProductsWithoutOrderDto> withoutOrderDtoList = new ArrayList<>();

            if(entityDto.getProducts().size() == 0){
                List<OrderProducts> orderProductsList = orderProductsRepository.findOrderProductsByOrderId(entityDto.getId());
                if (orderProductsList.size() != 0) {
                    for (OrderProducts orderProducts: orderProductsList) {
                        orderProductsRepository.deleteById(orderProducts.getId());
                    }
                }
            } else {
                List<OrderProducts> orderProductsList = orderProductsRepository.findOrderProductsByOrderId(id);
                if (orderProductsList.size() != 0) {
                    for (OrderProducts products: orderProductsList) {
                        long idBuscado = 0;
                        for (OProductsWithoutOrderDto oProductsWithoutOrderDto: entityDto.getProducts()) {
                            if (Objects.nonNull(oProductsWithoutOrderDto.getId())){
                                if (oProductsWithoutOrderDto.getId() == products.getId()){
                                    idBuscado = oProductsWithoutOrderDto.getId();
                                }
                            }
                        }
                        if (idBuscado==0){
                            orderProductsRepository.deleteById(products.getId());
                        }
                    }
                }

                for (OProductsWithoutOrderDto withoutOrderDto: entityDto.getProducts()) {
                    OrderProducts orderProducts;
                    if(Objects.isNull(withoutOrderDto.getId())){
                        orderProducts = new OrderProducts();
                    }else {
                        Optional<OrderProducts> orderProductsOptional = orderProductsRepository.findById(withoutOrderDto.getId());
                        orderProducts = orderProductsOptional.get();
                    }
                    orderProducts.setOrder(entityUpdate);
                    orderProducts.setProduct(modelMapper.map(withoutOrderDto.getProduct(), Product.class));
                    orderProducts.setCant(withoutOrderDto.getCant());
                    orderProducts = orderProductsRepository.save(orderProducts);
                    modelMapper.map(orderProducts, withoutOrderDto);
                    withoutOrderDtoList.add(withoutOrderDto);
                }
            }
            entityDto = mapper.convertToDto(entityUpdate);
            entityDto.setProducts(withoutOrderDtoList);

            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public boolean deleteById(Long id) throws CustomException {
        try {
            if (orderRepository.existsById(id)) {

                List<OrderProducts> orderProductsList = orderProductsRepository.findOrderProductsByOrderId(id);
                for (OrderProducts orderProducts: orderProductsList) {
                    orderProductsRepository.deleteById(orderProducts.getId());
                }
                orderRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }




    public Long getTotalCookingTime(OrderDto orderDto) throws CustomException {
        try{
            Long totalCookingTime = 0L;
            for (OProductsWithoutOrderDto productOrderCant: orderDto.getProducts()) {
                ProductDto productFound = productService.findById(productOrderCant.getProduct().getId());
                if (totalCookingTime < productFound.getCookingTime()){
                    totalCookingTime = productFound.getCookingTime();
                }
            }

            totalCookingTime+=5L;

            if (orderDto.getWithdrawalMode().equals("Delivery")){
                totalCookingTime+=10L;
            }

            return totalCookingTime;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

}
