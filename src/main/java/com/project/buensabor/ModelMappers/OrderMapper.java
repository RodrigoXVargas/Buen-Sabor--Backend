package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.orderDto.OrderDto;
import com.project.buensabor.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends ModelMapperEntity<Order, OrderDto> {
}
