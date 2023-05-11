package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.*;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CATEGORIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="@id")
public class Category extends Base {
    @Column
    private String name;

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "category-subcategories")
    private List<Category> subcategories;

    @ManyToOne(cascade= {CascadeType.MERGE})
    @JoinColumn(name="category_fk")
    @JsonBackReference(value = "category-subcategories")
    private Category category;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "subcategory-products")
    private List<Product> products;

}