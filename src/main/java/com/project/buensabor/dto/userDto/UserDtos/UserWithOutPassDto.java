package com.project.buensabor.dto.userDto.UserDtos;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderWithoutuserDto;
import com.project.buensabor.dto.userDto.AddressDtos.AddressWithoutuserDto;
import com.project.buensabor.entities.Rol;
import com.project.buensabor.enums.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithOutPassDto extends BaseDto {

    private String firstName;

    private String lastName;

    private Long telephone;

    private String mail;

    private StatusUser blacklist;

    private Rol rol;

    private List<AddressWithoutuserDto> addresses;

    private List<OrderWithoutuserDto> orders;
}
