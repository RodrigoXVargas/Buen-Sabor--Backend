package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends ModelMapperEntity<Product, ProductDto> {
}
