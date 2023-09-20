package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.IngredientMapper;
import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.IngredientRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl extends BaseServicesDTOImpl<Ingredient, IngredientDto, IngredientMapper, Long> implements IngredientService {


    public IngredientServiceImpl(BaseRepository<Ingredient, Long> baseRepository, IngredientMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public boolean validarStock(Long idIngredient, Long cantRequerida) throws CustomException {
        try{
            Optional<Ingredient> ingredientOptional = ingredientRepository.findById(idIngredient);
            if (!ingredientOptional.isPresent()){
                new CustomException("No se encuentra el ingrediente");
            }
            Ingredient ingredient = ingredientOptional.get();

            if (ingredient.getStock()-cantRequerida>0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void descontarStock(Long idIngredient, Long cantADescontar) throws CustomException {
        try{
            Optional<Ingredient> ingredientOptional = ingredientRepository.findById(idIngredient);
            if (!ingredientOptional.isPresent()){
                new CustomException("No se encuentra el ingrediente");
            }
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setStock(ingredient.getStock()-cantADescontar);
            ingredient = ingredientRepository.save(ingredient);
            //System.out.println("se desconto "+ cantADescontar+" del ingrediente "+ ingredient.getName());

        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<IngredientDto> getIngOrderStockMin() throws CustomException {
        try{
            List<Ingredient> ingredientList = ingredientRepository.getIngredientsOrderStockMin();
            List<IngredientDto> ingredientDtoList = new ArrayList<>();
            for (Ingredient ingredient: ingredientList) {
                ingredientDtoList.add(mapper.convertToDto(ingredient));
            }
            return ingredientDtoList;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }


}
