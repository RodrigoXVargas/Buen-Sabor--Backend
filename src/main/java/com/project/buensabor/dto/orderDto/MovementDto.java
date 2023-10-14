package com.project.buensabor.dto.orderDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.entities.Order;
import com.project.buensabor.enums.TypeMovement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementDto extends BaseDto {

    private TypeMovement type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Buenos_Aires")
    private LocalDateTime date;

    private String description;

    private Double total;

    private OrderDto order;

}
