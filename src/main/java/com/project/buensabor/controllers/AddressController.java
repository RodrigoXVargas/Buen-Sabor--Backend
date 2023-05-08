package com.project.buensabor.controllers;

import com.project.buensabor.dto.userDto.AddressDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.services.AddressService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAddress()
    {
        List<AddressDto> list = addressService.getAllCategories();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public AddressDto getAddressByID(@PathVariable("id") Long addressID)
    {
        AddressDto address = addressService.getAddress(addressID);
        return address;
    }

    @PostMapping("/save")
    public Address saveAddress(@Valid @RequestBody Address address)
    {
        return addressService.saveAddress(address);
    }

    @PutMapping("/update/{id}")
    public Address updateAddress(@RequestBody Address address, @PathVariable("id") Long addressID)
    {
        return addressService.updateAddress(address, addressID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAddressById(@PathVariable("id") Long addressID)
    {
        addressService.deleteAddressById(addressID);
        return "Deleted Successfully";
    }
}
