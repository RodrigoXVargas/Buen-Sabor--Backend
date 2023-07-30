package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.AddressDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.ModelMappers.AddressMapper;
import com.project.buensabor.entities.ModelMappers.ModelMapperEntity;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServicesDTOImpl<Address, AddressDto, AddressMapper, Long> implements AddressService {


    public AddressServiceImpl(BaseRepository<Address, Long> baseRepository, AddressMapper mapper) {
        super(baseRepository, mapper);
    }
}
