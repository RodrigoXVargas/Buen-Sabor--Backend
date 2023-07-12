package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCTS_INGREDIENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductIngredient  extends Base {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_fk")
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ingredient_fk")
    private Ingredient ingredient;

    @Column(name = "cantidad")
    private Long cant;

}
