package com.project.buensabor.controllers;

import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.entities.Ingredient;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllIngredient()
    {
        List<IngredientDto> list = ingredientService.getAllIngredientes();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public IngredientDto getIngredientByID(@PathVariable("id") Long ingredientID)
    {
        IngredientDto ingredient = ingredientService.getIngredient(ingredientID);
        return ingredient;
    }

    @PostMapping("/save")
    public Ingredient saveIngredient(@Valid @RequestBody Ingredient ingredient)
    {
        return ingredientService.saveIngredient(ingredient);
    }

    @PutMapping("/update/{id}")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient, @PathVariable("id") Long ingredientID)
    {
        return ingredientService.updateIngredient(ingredient, ingredientID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteIngredientById(@PathVariable("id") Long ingredientID)
    {
        ingredientService.deleteIngredientById(ingredientID);
        return "Deleted Successfully";
    }
}
