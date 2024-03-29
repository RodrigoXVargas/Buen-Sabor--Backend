package com.project.buensabor.repositories;

import com.project.buensabor.entities.Category;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {


}