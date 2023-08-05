package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.ProductIngredientDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface ProductIngredientService extends BaseServicesDTO<ProductIngredientDto, Long> {

    public List<PIngredientsCantDto> ingredientsByProductId(Long id) throws Exception;
}
