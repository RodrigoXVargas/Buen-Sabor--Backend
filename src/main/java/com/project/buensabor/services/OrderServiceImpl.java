package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.OrderMapper;
import com.project.buensabor.dto.orderDto.OrderDto;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.dto.userDto.AddressDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Order;
import com.project.buensabor.entities.OrderProducts;
import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.OrderProductsRepository;
import com.project.buensabor.repositories.OrderRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl extends BaseServicesDTOImpl<Order, OrderDto, OrderMapper, Long> implements OrderService {


    public OrderServiceImpl(BaseRepository<Order, Long> baseRepository, OrderMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<OrderDto> ordersByUserId(Long id) throws Exception {
        try {
            List<Order> entities = orderRepository.findOrdersByUserId(id);
            List<OrderDto> entitiesDtos = new ArrayList<>();
            if (!entities.isEmpty()){
                for (Order entity: entities) {
                    entitiesDtos.add(mapper.convertToDto(entity));
                }
            }
            return entitiesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional //Indica que el método es una transacción.
    public List<OrderDto> findAll() throws Exception {
        try {
            List<Order> entities = baseRepository.findAll();
            List<OrderDto> entitiesDtos = new ArrayList<>();
            for (Order entity : entities) {
                OrderDto orderDto = mapper.convertToDto(entity);
                orderDto.setProducts(getOrderProductsByOrder(orderDto));
                entitiesDtos.add(orderDto);
            }
            return entitiesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }



    @Override
    @Transactional
    public OrderDto findById(Long id) throws Exception {
        try {
            Optional<Order> entityOptional = orderRepository.findById(id);
            Order order = entityOptional.get();
            OrderDto orderDto = mapper.convertToDto(order);
            orderDto.setProducts(getOrderProductsByOrder(orderDto));
            return orderDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public List<OProductsWithoutOrderDto> getOrderProductsByOrder(OrderDto orderDto) {
        List<OrderProducts> orderProductsList = orderProductsRepository.findOrderProductsByOrderId(orderDto.getId());
        List<OProductsWithoutOrderDto> oProductsWithoutOrderDtos = new ArrayList<>();
        for (OrderProducts orderProducts: orderProductsList) {
            OProductsWithoutOrderDto withoutOrderDto = modelMapper.map( orderProducts, OProductsWithoutOrderDto.class);
            oProductsWithoutOrderDtos.add(withoutOrderDto);
        }
        return oProductsWithoutOrderDtos;
    }



    @Override
    @Transactional
    public OrderDto saveOne(OrderDto entityDto) throws Exception {
        try {
            Order entity = mapper.convertToEntity(entityDto);
            entity = orderRepository.save(entity);

            List<OProductsWithoutOrderDto> withoutOrderDtoList = new ArrayList<>();

            if (entityDto.getProducts().size()!=0) {
                for (OProductsWithoutOrderDto oProductsWithoutOrderDto : entityDto.getProducts()) {
                    OrderProducts orderProducts = new OrderProducts(
                            entity,
                            modelMapper.map(oProductsWithoutOrderDto.getProduct(), Product.class),
                            oProductsWithoutOrderDto.getCant()
                    );
                    orderProducts = orderProductsRepository.save(orderProducts);
                    withoutOrderDtoList.add(modelMapper.map(orderProducts, OProductsWithoutOrderDto.class));
                }
            }
            entityDto = mapper.convertToDto(entity);
            entityDto.setProducts(withoutOrderDtoList);
            return mapper.convertToDto(entity);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /*

    @Override
    @Transactional
    public F updateOne(F entity, ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(mapper.convertToEntity(entity));
            return mapper.convertToDto(entityUpdate);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
*/
}
