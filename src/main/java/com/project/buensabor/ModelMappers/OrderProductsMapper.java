package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.orderDto.OrderProductsDto;
import com.project.buensabor.entities.OrderProducts;
import org.springframework.stereotype.Component;

@Component
public class OrderProductsMapper extends ModelMapperEntity<OrderProducts, OrderProductsDto> {
}
