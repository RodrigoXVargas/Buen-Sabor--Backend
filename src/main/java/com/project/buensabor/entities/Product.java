package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product extends Base {
    @Column
    private String name;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean active;

    @Column
    private Long price;

    @ManyToOne()
    @JoinColumn(name = "subcategory")
    private Category subcategory;

    @ManyToMany
    @JoinTable(
            name = "Product_Ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;


    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}
