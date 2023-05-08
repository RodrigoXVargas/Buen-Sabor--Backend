package com.project.buensabor.repositories;

import com.project.buensabor.entities.Category;
import com.project.buensabor.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}