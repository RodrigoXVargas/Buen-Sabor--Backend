package com.project.buensabor.dto.productDto;

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
public class CategoryDto extends BaseDto {

    private String name;

    private Category parentCategory;

}
