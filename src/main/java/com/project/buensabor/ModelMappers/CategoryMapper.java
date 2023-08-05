package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends ModelMapperEntity<Category, CategoryDto> {
}
