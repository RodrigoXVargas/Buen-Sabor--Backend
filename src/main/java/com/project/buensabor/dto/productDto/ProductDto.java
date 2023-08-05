package com.project.buensabor.dto.productDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.ProductIngredientDto;
import com.project.buensabor.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends BaseDto {

    private String name;

    private boolean active;

    private Long price;

    private Long cookingTime;

    private String image;

    private Category subcategory;

    private List<PIngredientsCantDto> ingredients;

}
