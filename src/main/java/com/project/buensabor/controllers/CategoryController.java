package com.project.buensabor.controllers;

import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Category;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory()
    {
        List<CategoryDto> list = categoryService.getAllCategories();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public CategoryDto getCategoryByID(@PathVariable("id") Long categoryID)
    {
        CategoryDto category = categoryService.getCategory(categoryID);
        return category;
    }

    @PostMapping("/save")
    public Category saveCategory(@Valid @RequestBody Category category)
    {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable("id") Long categoryID)
    {
        return categoryService.updateCategory(category, categoryID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategoryById(@PathVariable("id") Long categoryID)
    {
        categoryService.deleteCategoryById(categoryID);
        return "Deleted Successfully";
    }
}
