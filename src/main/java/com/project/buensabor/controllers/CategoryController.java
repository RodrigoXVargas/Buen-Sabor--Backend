package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Category;
import com.project.buensabor.services.AddressServiceImpl;
import com.project.buensabor.services.CategoryServiceImpl;
import com.project.buensabor.services.interfaces.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/categories")
public class CategoryController extends BaseControllerImpl<Category, CategoryServiceImpl> {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyRole('admin', 'superAdmin')")
    @GetMapping(value = "/{name}")
    public Category getByName(String name){
        Category category = null;
        try{
            if(categoryService.existByName(name)){
                Optional<Category> optionalCategory = categoryService.findByName(name);
            }
        }catch (Exception e){
            log.info(e.getMessage(), e);
        }
        return category;
    }
}
