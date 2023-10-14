package com.project.buensabor.dto.productDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Measure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto extends BaseDto {

    private String name;

    private Long stock;

    private Double cost;

    private Long stockMin;

    private Measure measure;

}
