package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Base {


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    @Column
    private String withdrawalMode;

    @Column
    private Double totalPrice;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paymode_fk")
    private Paymode paymode;

    @Column
    private String address;

    @Column
    private Long totalCookingTime = 0L;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_fk")
    private StatusOrder statusOrder;


}
