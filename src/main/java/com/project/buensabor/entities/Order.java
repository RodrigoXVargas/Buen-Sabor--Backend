package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Base {

    @Column
    private String date;

    @Column
    private String withdrawalMode;

    @Column
    private Double totalPrice;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paymode_fk")
    private Paymode paymode;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_fk")
    private Address address;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_fk")
    private StatusOrder statusOrder;


}
