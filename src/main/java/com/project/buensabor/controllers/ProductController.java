package com.project.buensabor.controllers;

import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Product;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProduct()
    {
        List<ProductDto> list = productService.getAllCategories();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public ProductDto getProductByID(@PathVariable("id") Long productID)
    {
        ProductDto product = productService.getProduct(productID);
        return product;
    }

    @PostMapping("/save")
    public Product saveProduct(@Valid @RequestBody Product product)
    {
        return productService.saveProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") Long productID)
    {
        return productService.updateProduct(product, productID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long productID)
    {
        productService.deleteProductById(productID);
        return "Deleted Successfully";
    }
}
