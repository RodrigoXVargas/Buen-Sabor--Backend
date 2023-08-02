package com.project.buensabor.services;

import com.project.buensabor.dto.orderDto.OrderDto;
import com.project.buensabor.entities.ModelMappers.OrderMapper;
import com.project.buensabor.entities.Order;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseServicesDTOImpl<Order, OrderDto, OrderMapper, Long> implements OrderService {


    public OrderServiceImpl(BaseRepository<Order, Long> baseRepository, OrderMapper mapper) {
        super(baseRepository, mapper);
    }
}
