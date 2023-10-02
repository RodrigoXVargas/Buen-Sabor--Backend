package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.dto.productDto.ProductDtos.ProductDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.services.ProductServiceImpl;
import com.project.buensabor.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
            return ResponseEntity.internalServerError().body("Error al obtener los productos activos: "+ System.lineSeparator()+ e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('product:getProductsByQuantity','_superAdmin')")
    @GetMapping(value = "/getProductsByQuantity/{desde}&{hasta}")
    public ResponseEntity<?> getProductsByQuantity(
            @PathVariable String desde,
            @PathVariable String hasta){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getBestSellingProducts(desde, hasta));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener los productos por cantidad vendida por fechas: "+ System.lineSeparator()+ e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('product:getByQuanSold','_superAdmin')")
    @GetMapping(value = "/getByQuanSold")
    public ResponseEntity<?> getByQuanSold(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.findProductsByQDesc());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener los productos por cantidad vendida: "+ System.lineSeparator()+ e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('product:changeActive','_superAdmin')")
    @GetMapping(value = "/changeActive/{id}")
    public ResponseEntity<?> changeActiveProduct(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.changeActive(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al cambiar el estado del producto: "+ System.lineSeparator()+ e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('product:validarStock','_superAdmin')")
    @GetMapping(value = "/validarStock")
    public ResponseEntity<?> validarStock(@RequestBody List<OProductsWithoutOrderDto> productosAValidar){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.validarStock(productosAValidar));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al validar Stock: "+ e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('product:save','_superAdmin')")
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveOne(
            @RequestPart("productDto") ProductDto productDto,
            @RequestParam("image") MultipartFile image) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.saveOne(productDto, image));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar el producto: "+ System.lineSeparator()+ e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('product:update','_superAdmin')")
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateOne(
            @RequestPart("productDto") ProductDto productDto,
            @PathVariable Long id,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.updateOne(productDto, id, image));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar el producto: "+ System.lineSeparator()+ e.getMessage());
        }
    }


    @PreAuthorize("hasAnyAuthority('product:delete','_superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al borrar el producto: "+ System.lineSeparator()+ e.getMessage());
        }
    }
}
