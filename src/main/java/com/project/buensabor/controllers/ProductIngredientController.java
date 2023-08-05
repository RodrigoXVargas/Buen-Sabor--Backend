package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.ProductIngredientDto;
import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.services.ProductIngredientServiceImpl;
import com.project.buensabor.services.interfaces.ProductIngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/productingredients")
public class ProductIngredientController extends BaseControllerImpl<ProductIngredient, ProductIngredientDto, ProductIngredientServiceImpl> {

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
}
