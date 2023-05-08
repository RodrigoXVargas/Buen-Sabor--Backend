package com.project.buensabor.repositories;

import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Measure;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends BaseRepository<Ingredient, Long> {

}