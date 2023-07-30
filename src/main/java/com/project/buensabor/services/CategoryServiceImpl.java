package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Category;
import com.project.buensabor.entities.ModelMappers.CategoryMapper;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl extends BaseServicesDTOImpl<Category, CategoryDto, CategoryMapper, Long> implements CategoryService {


    public CategoryServiceImpl(BaseRepository<Category, Long> baseRepository, CategoryMapper mapper) {
        super(baseRepository, mapper);
    }
}
