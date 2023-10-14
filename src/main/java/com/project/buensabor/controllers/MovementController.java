package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.orderDto.MovementDto;
import com.project.buensabor.entities.Movement;
import com.project.buensabor.services.MovementServiceImpl;
import com.project.buensabor.services.interfaces.MovementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/movements")
public class MovementController extends BaseControllerImpl<Movement, MovementDto, MovementServiceImpl> {

    @Autowired
    private MovementService movementService;

    @Override
    @PreAuthorize("hasAnyAuthority('movement:getAll', '_superAdmin')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(movementService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('movement:getMovementsByDates', '_superAdmin')")
    @GetMapping("/getMovementsByDates/{desde}&{hasta}&{type}")
    public ResponseEntity<?> getMovementsByDates(
            @PathVariable LocalDate desde,
            @PathVariable LocalDate hasta,
            @PathVariable String type) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(movementService.getMovementsByDates(desde, hasta, type));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('movement:getOne', '_superAdmin')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(movementService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('movement:save', '_superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody MovementDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(movementService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }


    @PreAuthorize("hasAnyAuthority('movement:update', '_superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody MovementDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(movementService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }


    @PreAuthorize("hasAnyAuthority('movement:delete', '_superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(movementService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
