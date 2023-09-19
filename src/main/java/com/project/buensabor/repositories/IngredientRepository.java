package com.project.buensabor.repositories;

import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends BaseRepository<Ingredient, Long> {

    @Query(value = "SELECT * FROM ingredients order by (ingredients.stock-ingredients.stock_min) asc", nativeQuery = true)
    List<Ingredient> getIngredientsOrderStockMin();


}