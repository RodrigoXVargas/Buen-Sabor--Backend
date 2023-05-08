package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CATEGORIES")
public class Category extends Base {
    @Column
    private String name;

    @OneToMany(mappedBy="subcategory_to", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Category> category;

    @ManyToOne(cascade= {CascadeType.MERGE})
    @JoinColumn(name="subcategory_to")
    private Category subcategory_to;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;

}