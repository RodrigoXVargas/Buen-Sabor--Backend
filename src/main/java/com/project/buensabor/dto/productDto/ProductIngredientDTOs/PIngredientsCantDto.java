package com.project.buensabor.dto.productDto.ProductIngredientDTOs;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PIngredientsCantDto extends BaseDto {
    private Ingredient ingredient;

    private Long cant;
}

