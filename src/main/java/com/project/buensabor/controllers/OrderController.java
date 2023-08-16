package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.entities.Order;
import com.project.buensabor.services.OrderServiceImpl;
import com.project.buensabor.services.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/orders")
public class OrderController extends BaseControllerImpl<Order, OrderDto, OrderServiceImpl> {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/getOrdersByStatus/{id}")
    public ResponseEntity<?> getOrdersByStatus(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByStatus(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PutMapping(value = "/changeStatus/{id}")
    public ResponseEntity<?> changeStatusOrder(@PathVariable Long id, @RequestBody StatusOrderDto status){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.changeStatus(status, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
