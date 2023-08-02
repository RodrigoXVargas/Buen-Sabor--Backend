package com.project.buensabor.dto.productDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Product;
;

public class ProductIngredientsDto extends BaseDto {

    private Product product;
    private Ingredient ingredient;
    private Long cant;
}
