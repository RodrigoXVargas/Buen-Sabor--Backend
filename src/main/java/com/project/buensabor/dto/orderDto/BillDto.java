package com.project.buensabor.dto.orderDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDto extends BaseDto {
    private OrderDto order;

    private String date;

}
