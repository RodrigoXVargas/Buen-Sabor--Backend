package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.entities.Product;
import com.project.buensabor.services.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/products")
public class ProductController extends BaseControllerImpl<Product, ProductServiceImpl> {
}