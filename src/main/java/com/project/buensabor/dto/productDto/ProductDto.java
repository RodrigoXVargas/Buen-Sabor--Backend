package com.project.buensabor.dto.productDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Category;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends BaseDto {

    private String name;

    private boolean active;

    private Long price;

    private Category subcategory;

    private List<Ingredient> ingredients;
}
