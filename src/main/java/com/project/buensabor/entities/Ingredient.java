package com.project.buensabor.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingredient;

    @Column
    private String name;
    @Column
    private Long stock;
    @Column
    private Long cost;

    @ManyToOne()
    @JoinColumn(name = "measure_id")
    private Measure measure;

    @ManyToMany(mappedBy = "ingredients")
    private List<Product> products;

}
