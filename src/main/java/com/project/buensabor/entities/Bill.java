package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "BILLS")
public class Bill extends Base {

    @Column
    private Instant date;

    @JoinColumn(name = "order_id")
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "order-bill")
    private Order order;

}
