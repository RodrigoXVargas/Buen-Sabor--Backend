package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.CategoryMapper;
import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Category;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServicesDTOImpl<Category, CategoryDto, CategoryMapper, Long> implements CategoryService {


    public CategoryServiceImpl(BaseRepository<Category, Long> baseRepository, CategoryMapper mapper) {
        super(baseRepository, mapper);
    }
}
