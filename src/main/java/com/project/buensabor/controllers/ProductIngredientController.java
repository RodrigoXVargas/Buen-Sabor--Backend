package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.MeasureDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientDto;
import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.services.ProductIngredientServiceImpl;
import com.project.buensabor.services.interfaces.MeasureService;
import com.project.buensabor.services.interfaces.ProductIngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/productingredients")
public class ProductIngredientController extends BaseControllerImpl<ProductIngredient, PIngredientDto, ProductIngredientServiceImpl> {

    @Autowired
    private ProductIngredientService ingredientService;

    @GetMapping(value = "/ingredientsByProduct/{id}")
    public ResponseEntity<?> getIngredientsByProductId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredientService.ingredientsByProductId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }


    @PreAuthorize("hasAnyAuthority('productingredients:save','superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody PIngredientDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredientService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('productingredients:update','superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody PIngredientDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredientService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('productingredients:delete','superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ingredientService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
