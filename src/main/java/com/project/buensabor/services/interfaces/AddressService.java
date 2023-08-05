package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.dto.userDto.AddressDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface AddressService extends BaseServicesDTO<AddressDto, Long> {

    public List<AddressDto> addressesByUserId(Long id) throws Exception;
}
