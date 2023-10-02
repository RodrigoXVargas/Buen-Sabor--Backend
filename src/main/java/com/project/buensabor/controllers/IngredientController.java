package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.services.IngredientServiceImpl;
import com.project.buensabor.services.interfaces.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/ingredients")
public class IngredientController extends BaseControllerImpl<Ingredient, IngredientDto, IngredientServiceImpl> {

    @Autowired
    private IngredientService ingredientService;


    @PreAuthorize("hasAnyAuthority('ingredient:save', '_superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody IngredientDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredientService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('ingredient:update', '_superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody IngredientDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredientService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('ingredient:delete', '_superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ingredientService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('ingredient:reponerStock', '_superAdmin')")
    @PutMapping("/reponerStock")
    public ResponseEntity<?> reponerStock(@RequestBody List<PIngredientsCantDto> ingredientList) {
        try {
            for (PIngredientsCantDto ingredientCant: ingredientList) {
                ingredientService.descontarOReponerStock(ingredientCant.getIngredient().getId(), ingredientCant.getCant(), true);
            }
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('ingredient:getAllOrderStockMin', '_superAdmin')")
    @GetMapping("/getAllOrderStockMin")
    public ResponseEntity<?> getAllOrderStockMin() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredientService.getIngOrderStockMin());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener los ingredientes ordenados por stockMin: " + System.lineSeparator() + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ingredient:excelDownload', '_superAdmin')")
    @PostMapping("/excelDownload")
    public ResponseEntity<?> excelDownload(@RequestBody ArrayList<IngredientDto> entities) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredientService.excelDownload(entities));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
