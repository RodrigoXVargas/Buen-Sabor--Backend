package com.project.buensabor.dto.userDto.AddressDtos;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.userDto.LocationDto;
import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.Location;
import com.project.buensabor.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto extends BaseDto {

    private String street;
    private Integer number;
    private UserDto user;
    private LocationDto location;


}