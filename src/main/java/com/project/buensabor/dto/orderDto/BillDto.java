package com.project.buensabor.dto.orderDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDto extends BaseDto {
    private Order order;

    private String date;

}
