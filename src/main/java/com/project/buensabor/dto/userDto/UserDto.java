package com.project.buensabor.dto.userDto;

import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Order;
import com.project.buensabor.entities.Rol;
import com.project.buensabor.enums.StatusUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Long telephone;
    private String mail;
    private String password;
    private StatusUser blacklist;
    private Rol rol;
    private List<Address> addresses;
    private List<Order> orders;
}
