package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "INGREDIENTS")
@Getter
@Setter
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
    private Measure measure_id;

    @ManyToMany(mappedBy = "ingredients")
    private List<Product> products;

}
