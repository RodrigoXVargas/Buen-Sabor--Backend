package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface ProductIngredientService extends BaseServicesDTO<PIngredientDto, Long> {

    public List<PIngredientsCantDto> ingredientsByProductId(Long id) throws Exception;
}
