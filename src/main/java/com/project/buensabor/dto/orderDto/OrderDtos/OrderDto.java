package com.project.buensabor.dto.orderDto.OrderDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.dto.userDto.UserDtos.UserDto;
import com.project.buensabor.entities.Paymode;
import com.project.buensabor.entities.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends BaseDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Buenos_Aires")
    private LocalDateTime creationDate;

    private Long totalCookingTime;

    private String withdrawalMode;

    private Double totalPrice;

    private Paymode paymode;

    private String address;

    private UserDto user;

    private StatusOrder statusOrder;

    private List<OProductsWithoutOrderDto> products;

}
