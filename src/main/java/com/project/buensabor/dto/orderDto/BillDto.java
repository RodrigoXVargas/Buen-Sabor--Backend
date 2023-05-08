package com.project.buensabor.dto.orderDto;

import com.project.buensabor.entities.Order;
import jakarta.persistence.*;

import java.time.Instant;

public class BillDto {
    private Order order;

    private Instant date;

}
