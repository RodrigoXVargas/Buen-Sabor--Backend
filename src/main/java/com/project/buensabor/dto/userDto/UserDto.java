package com.project.buensabor.dto.userDto;

import com.project.buensabor.entities.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    private String password;

    private String mail;

    private Boolean blacklist;

    private Rol rol;

}
