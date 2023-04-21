package com.project.buensabor.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    @Column
    private String name;

    @Column
    private boolean active;

    @Column
    private Long price;

    @ManyToOne()
    @JoinColumn(name = "subcategory_id")
    private Category subcategory;

    @ManyToMany
    @JoinTable(
            name = "Product_Ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

}
