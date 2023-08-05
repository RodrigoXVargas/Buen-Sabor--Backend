package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INGREDIENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient extends Base {

    @Column
    private String name;

    @Column
    private Long stock;

    @Column
    private Double cost;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "measure_fk")
    private Measure measure;


}
