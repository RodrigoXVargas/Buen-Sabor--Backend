package com.project.buensabor.services;

import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.ProductIngredientService;
import org.springframework.stereotype.Service;

@Service
public class ProductIngredientServiceImpl extends BaseServicesImpl<ProductIngredient, Long> implements ProductIngredientService {

    public ProductIngredientServiceImpl(BaseRepository<ProductIngredient, Long> baseRepository) {super(baseRepository);}
}
