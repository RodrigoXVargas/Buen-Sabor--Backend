package com.project.buensabor.dto.orderDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends BaseDto {

    private Instant date;

    private User user;

    private Address address;

    private String withdrawalMode;

    private Bill bill;

    private StatusOrder statusOrder;

    private List<Product> products;
//    private Payment formPayment;
}
