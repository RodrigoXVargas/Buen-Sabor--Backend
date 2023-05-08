package com.project.buensabor.services;

import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.IngredientService;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl extends BaseServicesImpl<Ingredient, Long> implements IngredientService {

    public IngredientServiceImpl(BaseRepository<Ingredient, Long> baseRepository) {
        super(baseRepository);
    }

}
