package com.project.buensabor.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "BILLS")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bill;

    @JoinColumn(name = "order_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column
    private Instant date;

}
