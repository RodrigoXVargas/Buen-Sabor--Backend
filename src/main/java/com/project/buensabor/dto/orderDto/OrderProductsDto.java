package com.project.buensabor.dto.orderDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Order;
import com.project.buensabor.entities.Product;

public class OrderProductsDto extends BaseDto {

    private Order order;
    private Product product;
    private Long cant;
}
