package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "MEASURES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Measure extends Base {

    @Column
    private String measure;

    @OneToMany(mappedBy = "measure", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "measure-ingredients")
    private List<Ingredient> ingredients;

}
