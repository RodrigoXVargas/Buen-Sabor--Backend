package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.OrderMapper;
import com.project.buensabor.dto.orderDto.OrderDto;
import com.project.buensabor.dto.userDto.AddressDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Order;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.OrderRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl extends BaseServicesDTOImpl<Order, OrderDto, OrderMapper, Long> implements OrderService {


    public OrderServiceImpl(BaseRepository<Order, Long> baseRepository, OrderMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private OrderRepository orderRepository;

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
}
