package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.entities.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper extends ModelMapperEntity<Ingredient, IngredientDto> {
}
