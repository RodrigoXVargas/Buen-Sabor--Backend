package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.AddressMapper;
import com.project.buensabor.dto.userDto.AddressDtos.AddressDto;
import com.project.buensabor.dto.userDto.AddressDtos.AddressWithoutuserDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.repositories.AddressRepository;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl extends BaseServicesDTOImpl<Address, AddressDto, AddressMapper, Long> implements AddressService {


    public AddressServiceImpl(BaseRepository<Address, Long> baseRepository, AddressMapper mapper) {
        super(baseRepository, mapper);
    }

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressWithoutuserDto> addressesByUserId(Long id) throws Exception {
        try {
            List<Address> entities = addressRepository.findAddressesByUserId(id);
            List<AddressWithoutuserDto> entitiesDtos = new ArrayList<>();
            if (!entities.isEmpty()){
                for (Address entity: entities) {
                    entitiesDtos.add(modelMapper.map(entity, AddressWithoutuserDto.class));
                }
            }
            return entitiesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
