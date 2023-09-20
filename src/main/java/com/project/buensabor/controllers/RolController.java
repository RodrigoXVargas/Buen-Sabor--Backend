package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.entities.Rol;
import com.project.buensabor.services.RolServiceImpl;
import com.project.buensabor.services.interfaces.RolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/rols")
public class RolController extends BaseControllerImpl<Rol, RolDto, RolServiceImpl> {

    @Autowired
    private RolService rolService;

    @Override
    @PreAuthorize("hasAnyAuthority('rol:getAll','_superAdmin')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rolService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('rol:getOne','_superAdmin')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rolService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('rol:save','_superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody RolDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rolService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('rol:update','_superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody RolDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rolService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('rol:delete','_superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rolService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
