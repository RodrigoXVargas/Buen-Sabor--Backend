package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.orderDto.BillDto;
import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Category;
import com.project.buensabor.services.CategoryServiceImpl;
import com.project.buensabor.services.interfaces.BillService;
import com.project.buensabor.services.interfaces.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/categories")
public class CategoryController extends BaseControllerImpl<Category, CategoryDto, CategoryServiceImpl> {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyAuthority('category:save', 'superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody CategoryDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('category:update', 'superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody CategoryDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('category:delete', 'superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(categoryService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
