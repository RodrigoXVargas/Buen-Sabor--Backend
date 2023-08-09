package com.project.buensabor.dto.orderDto.OrderProductsDtos;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Order;
import com.project.buensabor.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OProductsWithoutOrderDto extends BaseDto {

    private ProductDto product;
    private Long cant;
}
