package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientDto;
import com.project.buensabor.entities.ProductIngredient;
import org.springframework.stereotype.Component;

@Component
public class ProductIngredientsMapper extends ModelMapperEntity<ProductIngredient, PIngredientDto> {
}
