package com.project.buensabor.dto.productDto;

import com.project.buensabor.entities.Category;
import com.project.buensabor.entities.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    private String name;

    private Category subcategory_to;

}
