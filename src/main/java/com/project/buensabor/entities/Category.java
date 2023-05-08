package com.project.buensabor.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CATEGORIES")
public class Category{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_category;

    @Column
    private String name;

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
    private List<Category> subcategory;

    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name="subcategory_to")
    private Category category;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

}