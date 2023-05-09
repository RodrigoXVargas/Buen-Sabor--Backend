package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient extends Base {

    @Column
    private String name;

    @Column
    private Long stock;

    @Column
    private Long cost;

    @ManyToOne()
    @JoinColumn(name = "measure_fk")
    @JsonBackReference(value = "measure-ingredients")
    private Measure measure;

    @ManyToMany(mappedBy = "ingredients")
    private List<Product> products;

}
