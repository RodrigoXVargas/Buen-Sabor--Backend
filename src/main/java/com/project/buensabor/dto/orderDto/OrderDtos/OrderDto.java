package com.project.buensabor.dto.orderDto.OrderDtos;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.dto.userDto.AddressDtos.AddressWithoutuserDto;
import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Paymode;
import com.project.buensabor.entities.StatusOrder;
import com.project.buensabor.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends BaseDto {

    private String date;

    private String withdrawalMode;

    private Double totalPrice;

    private Paymode paymode;

    private String address;

    private UserDto user;

    private StatusOrder statusOrder;

    private List<OProductsWithoutOrderDto> products;

}
