package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.ArrayList;
import java.util.List;

public interface IngredientService extends BaseServicesDTO<IngredientDto, Long> {

    boolean validarStock(Long idIngredient, Long cantRequerida) throws CustomException;

    void descontarOReponerStock(List<PIngredientsCantDto> ingredientsCantDtoList, boolean descontarOreponer) throws CustomException;

    List<IngredientDto> getIngOrderStockMin() throws CustomException;

    String excelDownload(ArrayList<IngredientDto> ingredientsDto) throws Exception;
}
