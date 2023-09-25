package com.project.buensabor.dto.orderDto.OrderProductsDtos;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.productDto.ProductDtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductsDto extends BaseDto {

    private OrderDto order;
    private ProductDto product;
    private Long cant;
}
