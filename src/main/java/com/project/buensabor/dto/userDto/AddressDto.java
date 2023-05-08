package com.project.buensabor.dto.userDto;

import com.project.buensabor.entities.Location;
import com.project.buensabor.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private String street;


    private Integer number;


    private User user;


    private Location location;


}
