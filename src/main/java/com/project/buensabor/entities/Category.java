package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CATEGORIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Base {

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_fk")
    private Category parentCategory;


}