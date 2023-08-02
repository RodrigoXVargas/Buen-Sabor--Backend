package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.ModelMappers.IngredientMapper;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.IngredientService;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl extends BaseServicesDTOImpl<Ingredient, IngredientDto, IngredientMapper, Long> implements IngredientService {


    public IngredientServiceImpl(BaseRepository<Ingredient, Long> baseRepository, IngredientMapper mapper) {
        super(baseRepository, mapper);
    }
}
