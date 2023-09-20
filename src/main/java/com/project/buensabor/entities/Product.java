package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Base {
    @Column
    private String name;

    @Column
    private boolean active;

    @Column
    private Long price;

    @Column
    private Long cookingTime;

    @Column
    private String image;

    @Column
    private int quantitySold = 0;

    @Column
    private Double cost;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subcategory_fk")
    private Category subcategory;


}
