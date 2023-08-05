package com.project.buensabor.repositories;

import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductIngredientRepository extends BaseRepository<ProductIngredient, Long> {

    @Query(value = "SELECT * FROM products_ingredients WHERE products_ingredients.product_fk = :id", nativeQuery = true)
    List<ProductIngredient> findProductIngredientsByProductId (@Param("id") long id);

}
