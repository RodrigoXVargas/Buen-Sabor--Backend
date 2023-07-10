package com.project.buensabor.dto.orderDto;

import com.project.buensabor.entities.*;

import java.time.Instant;
import java.util.List;

public class OrderDto {

    private Instant date;

    private User user;

    private Address address;

    private String withdrawalMode;

    private Bill bill;

    private StatusOrder statusOrder;

    private List<Product> products;
//    private Payment formPayment;
}
