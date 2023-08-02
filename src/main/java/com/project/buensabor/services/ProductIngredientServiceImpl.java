package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.ProductIngredientsDto;
import com.project.buensabor.entities.ModelMappers.ProductIngredientsMapper;
import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.ProductIngredientService;
import org.springframework.stereotype.Service;

@Service
public class ProductIngredientServiceImpl extends BaseServicesDTOImpl<ProductIngredient, ProductIngredientsDto, ProductIngredientsMapper, Long> implements ProductIngredientService {


    public ProductIngredientServiceImpl(BaseRepository<ProductIngredient, Long> baseRepository, ProductIngredientsMapper mapper) {
        super(baseRepository, mapper);
    }
}
