package com.project.buensabor.dto.productDto;

import com.project.buensabor.entities.Category;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDto {

    private String name;

    private boolean active;

    private Long price;

    private Category subcategory;

    private List<Ingredient> ingredients;
}
