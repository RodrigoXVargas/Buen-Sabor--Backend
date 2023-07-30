package com.project.buensabor.dto.productDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Measure;
import com.project.buensabor.entities.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto extends BaseDto {

    private String name;

    private Long stock;

    private Long cost;

    private Measure measure_id;

    private List<Product> products;
}
