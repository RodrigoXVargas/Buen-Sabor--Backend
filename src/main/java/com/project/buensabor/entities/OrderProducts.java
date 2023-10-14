package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProducts extends Base {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_fk")
    private Order order;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_fk")
    private Product product;

    @Column(name = "cantidad")
    private Long cant;

}