package com.project.buensabor.repositories;

import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}