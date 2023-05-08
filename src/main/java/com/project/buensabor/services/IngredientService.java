package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.repositories.IngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {


    private final IngredientRepository ingredientRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<IngredientDto> getAllIngredientes() {
        List<IngredientDto> result = new ArrayList<>();
        List<Ingredient> list = ingredientRepository.findAll();

        for (Ingredient ingredient : list) {
            IngredientDto ingredientDto = IngredientDto.builder().name(ingredient.getName()).cost(ingredient.getCost()).stock(ingredient.getStock()).measure_id(ingredient.getMeasure_id()).build();
            result.add(ingredientDto);
        }
        return result;
    }

    public IngredientDto getIngredient(Long ingredientID) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientID);
        if(!(ingredient.isEmpty())) {
            return IngredientDto.builder().name(ingredient.get().getName()).cost(ingredient.get().getCost()).stock(ingredient.get().getStock()).measure_id(ingredient.get().getMeasure_id()).build();
        }
        return null;
    }

    public Ingredient updateIngredient(Ingredient ingredient, Long ingredientID) {
        Optional<Ingredient> ingredientList = ingredientRepository.findById(ingredientID);
        if (ingredientList.get().getId_ingredient() == ingredient.getId_ingredient()) {
            return ingredientRepository.save(ingredient);
        } else {
            return null;
        }
    }

    public void deleteIngredientById(Long ingredientID) {
        ingredientRepository.deleteById(ingredientID);
    }
}
