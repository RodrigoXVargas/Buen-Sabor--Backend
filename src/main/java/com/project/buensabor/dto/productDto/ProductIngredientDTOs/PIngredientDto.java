package com.project.buensabor.dto.productDto.ProductIngredientDTOs;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.dto.productDto.ProductDtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PIngredientDto extends BaseDto {
    private ProductDto product;

    private IngredientDto ingredient;

    private Long cant;
}
