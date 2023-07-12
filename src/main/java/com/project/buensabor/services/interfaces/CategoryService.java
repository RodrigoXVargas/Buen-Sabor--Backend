package com.project.buensabor.services.interfaces;

import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Category;
import com.project.buensabor.services.Base.BaseServices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryService extends BaseServices<Category, Long> {
/*
    Optional<Category> findByName(String name) throws Exception;

    boolean existByName(String name) throws Exception;
*/
}
