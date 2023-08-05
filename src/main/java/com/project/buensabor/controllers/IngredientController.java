package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.services.IngredientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/ingredients")
public class IngredientController extends BaseControllerImpl<Ingredient, IngredientDto, IngredientServiceImpl> {
}
