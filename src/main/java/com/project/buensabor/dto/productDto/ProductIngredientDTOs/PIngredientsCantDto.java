package com.project.buensabor.dto.productDto.ProductIngredientDTOs;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.productDto.IngredientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PIngredientsCantDto extends BaseDto {
    private IngredientDto ingredient;

    private Long cant;
}

