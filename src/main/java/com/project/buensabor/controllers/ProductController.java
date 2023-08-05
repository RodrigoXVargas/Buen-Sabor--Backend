package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.services.ProductServiceImpl;
import com.project.buensabor.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/products")
public class ProductController extends BaseControllerImpl<Product, ProductDto, ProductServiceImpl> {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getActives")
    public ResponseEntity<?> getActives(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.findProductsActive());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
