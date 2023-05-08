package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Category;
import com.project.buensabor.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> result = new ArrayList<>();
        List<Category> list = categoryRepository.findAll();

        for (Category category : list) {
            CategoryDto categoryDto = CategoryDto.builder().name(category.getName()).subcategory_to(category.getSubcategory_to()).build();
            result.add(categoryDto);
        }
        return result;
    }

    public CategoryDto getCategory(Long categoryID) {
        Optional<Category> category = categoryRepository.findById(categoryID);
        if(!(category.isEmpty())) {
            return CategoryDto.builder().name(category.get().getName()).subcategory_to(category.get().getSubcategory_to()).build();
        }
        return null;
    }

    public Category updateCategory(Category category, Long categoryID) {
        Optional<Category> categoryList = categoryRepository.findById(categoryID);
        if (categoryList.get().getId_category() == category.getId_category()) {
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }

    public void deleteCategoryById(Long categoryID) {
        categoryRepository.deleteById(categoryID);
    }
}
