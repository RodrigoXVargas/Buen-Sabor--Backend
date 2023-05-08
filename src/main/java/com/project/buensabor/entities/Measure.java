package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "MEASURES")
public class Measure extends Base {

    @Column
    private String measure;

    @OneToMany(mappedBy = "measure_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ingredient> ingredients;

}
