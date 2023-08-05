package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Category;
import com.project.buensabor.services.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/categories")
public class CategoryController extends BaseControllerImpl<Category, CategoryDto, CategoryServiceImpl> {

}
