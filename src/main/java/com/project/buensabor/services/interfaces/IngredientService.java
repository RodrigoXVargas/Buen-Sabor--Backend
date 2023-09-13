package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.Base.BaseServicesDTO;

public interface IngredientService extends BaseServicesDTO<IngredientDto, Long> {

    boolean validarStock(Long idIngredient, Long cantRequerida) throws CustomException;

    void descontarStock(Long idIngredient, Long cantADescontar) throws CustomException;
}
