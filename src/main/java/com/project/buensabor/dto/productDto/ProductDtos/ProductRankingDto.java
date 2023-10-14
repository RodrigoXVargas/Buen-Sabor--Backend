package com.project.buensabor.dto.productDto.ProductDtos;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRankingDto extends BaseDto {

    private String name;

    private boolean active;

    private Category subcategory_fk;

    private Double cost;

    private Double price;

    private Long quantity_sold;

    private Double total_cost;

    private Double total_profit;
}

