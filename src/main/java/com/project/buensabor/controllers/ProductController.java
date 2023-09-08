package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.MeasureDto;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.services.ProductServiceImpl;
import com.project.buensabor.services.interfaces.MeasureService;
import com.project.buensabor.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PreAuthorize("hasAnyAuthority('product:getByQuanSold','superAdmin')")
    @GetMapping(value = "/getByQuanSold")
    public ResponseEntity<?> getByQuanSold(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.findProductsByQDesc());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('product:changeActive','superAdmin')")
    @GetMapping(value = "/changeActive/{id}")
    public ResponseEntity<?> changeActiveProduct(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.changeActive(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('product:save','superAdmin')")
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveOne(
            @RequestPart("productDto") ProductDto productDto,
            @RequestParam("image") MultipartFile image) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.saveOne(productDto, image));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('product:update','superAdmin')")
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateOne(
            @RequestPart("productDto") ProductDto productDto,
            @PathVariable Long id,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.updateOne(productDto, id, image));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }


    @PreAuthorize("hasAnyAuthority('product:delete','superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
