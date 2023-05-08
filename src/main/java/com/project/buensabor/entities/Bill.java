package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "BILLS")
public class Bill extends Base {

    @JoinColumn(name = "order_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column
    private Instant date;

}
