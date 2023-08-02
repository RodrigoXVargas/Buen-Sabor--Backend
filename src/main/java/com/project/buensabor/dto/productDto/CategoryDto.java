package com.project.buensabor.dto.productDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Category;
import com.project.buensabor.entities.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends BaseDto {

    private String name;

    private Category subcategory_to;

}
