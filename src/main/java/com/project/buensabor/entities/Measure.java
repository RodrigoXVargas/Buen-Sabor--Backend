package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "MEASURES")
@Getter
@Setter
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_measure;

    @Column
    private String measure;

    @OneToMany(mappedBy = "measure_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ingredient> ingredients;

}
