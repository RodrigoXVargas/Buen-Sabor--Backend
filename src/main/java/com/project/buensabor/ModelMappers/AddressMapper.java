package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.userDto.AddressDtos.AddressDto;
import com.project.buensabor.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper extends ModelMapperEntity<Address, AddressDto> {
}
