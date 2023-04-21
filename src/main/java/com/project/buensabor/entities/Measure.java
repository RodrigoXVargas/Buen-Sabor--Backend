package com.project.buensabor.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MEASURES")
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_measure;

    @Column
    private String measure;

    @OneToMany(mappedBy = "measure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

}
