package com.project.buensabor.dto.userDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Order;
import com.project.buensabor.entities.Rol;
import com.project.buensabor.enums.StatusUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

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
