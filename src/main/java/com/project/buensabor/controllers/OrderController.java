package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.entities.Order;
import com.project.buensabor.services.OrderServiceImpl;
import com.project.buensabor.services.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/orders")
public class OrderController extends BaseControllerImpl<Order, OrderDto, OrderServiceImpl> {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyAuthority('order:getOrdersByStatus','_superAdmin')")
    @GetMapping(value = "/getOrdersByStatus/{id}")
    public ResponseEntity<?> getOrdersByStatus(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByStatus(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('order:changeStatus','_superAdmin')")
    @PutMapping(value = "/changeStatus/{id}")
    public ResponseEntity<?> changeStatusOrder(@PathVariable Long id, @RequestBody StatusOrderDto status){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.changeStatus(status, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('order:getAll','_superAdmin')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('order:getOne','_superAdmin')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('order:save','_superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody OrderDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar la orden"+e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('order:update','_superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody OrderDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('order:delete','_superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(orderService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
