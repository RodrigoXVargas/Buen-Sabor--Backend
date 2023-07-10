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

@Entity
@Table(name = "BILLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill extends Base {

    @Column
    private String date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_fk")
    private Order order;

}
