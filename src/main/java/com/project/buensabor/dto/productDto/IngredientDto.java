package com.project.buensabor.dto.productDto;

import com.project.buensabor.entities.Measure;
import com.project.buensabor.entities.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IngredientDto {

    private String name;

    private Long stock;

    private Long cost;

    private Measure measure_id;

    private List<Product> products;
}
