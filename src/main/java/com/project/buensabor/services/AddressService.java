package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.AddressDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {


    private final AddressRepository addressRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<AddressDto> getAllCategories() {
        List<AddressDto> result = new ArrayList<>();
        List<Address> list = addressRepository.findAll();

        for (Address address : list) {
            AddressDto addressDto = AddressDto.builder().street(address.getStreet()).user(address.getUser()).location(address.getLocation()).number(address.getNumber()).build();
            result.add(addressDto);
        }
        return result;
    }

    public AddressDto getAddress(Long addressID) {
        Optional<Address> address = addressRepository.findById(addressID);
        if(!(address.isEmpty())) {
            return AddressDto.builder().street(address.get().getStreet()).user(address.get().getUser()).location(address.get().getLocation()).number(address.get().getNumber()).build();
        }
        return null;
    }

    public Address updateAddress(Address address, Long addressID) {
        Optional<Address> addressList = addressRepository.findById(addressID);
        if (addressList.get().getId_address() == address.getId_address()) {
            return addressRepository.save(address);
        } else {
            return null;
        }
    }

    public void deleteAddressById(Long addressID) {
        addressRepository.deleteById(addressID);
    }
}
