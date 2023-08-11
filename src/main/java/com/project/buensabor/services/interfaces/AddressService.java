package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.userDto.AddressDtos.AddressDto;
import com.project.buensabor.dto.userDto.AddressDtos.AddressWithoutuserDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface AddressService extends BaseServicesDTO<AddressDto, Long> {

    public List<AddressWithoutuserDto> addressesByUserId(Long id) throws Exception;
}
