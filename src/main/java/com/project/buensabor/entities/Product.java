package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Base {
    @Column
    private String name;

    @Column
    private boolean active;

    @Column
    private Long price;

    @ManyToOne()
    @JoinColumn(name = "subcategory_fk")
    @JsonBackReference(value = "subcategory-products")
    private Category subcategory;

    @ManyToMany
    @JoinTable(
            name = "Product_Ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;


    @ManyToMany
    @JoinTable(
            name = "Product_Order",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Order> orders;

}
