package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.orderDto.MovementDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.time.LocalDate;
import java.util.List;

public interface MovementService extends BaseServicesDTO<MovementDto, Long> {


    List<MovementDto> getMovementsByDates(LocalDate desde, LocalDate hasta, String type) throws Exception;

    void saveRestoking(List<PIngredientsCantDto> ingredientList) throws Exception;
}
