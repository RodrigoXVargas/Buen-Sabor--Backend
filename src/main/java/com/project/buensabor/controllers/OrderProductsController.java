package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.orderDto.OrderProductsDto;
import com.project.buensabor.entities.OrderProducts;
import com.project.buensabor.services.OrderProductsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path = "/api/orderProducts")
public class OrderProductsController extends BaseControllerImpl<OrderProducts, OrderProductsDto, OrderProductsServiceImpl> {
}
