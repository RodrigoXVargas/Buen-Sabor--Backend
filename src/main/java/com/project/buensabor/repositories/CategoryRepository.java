package com.project.buensabor.repositories;

import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Category;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {

    /*@Query(value = "SELECT * FROM categories WHERE category.name = :name", nativeQuery = true)
    Optional<Category> findByName(@Param("name") String name);*/

    //boolean existByName(String name);

}