package com.project.buensabor.controllers;

import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.entities.Rol;
import com.project.buensabor.services.RolService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/rol")
public class RolController {
    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRoles()
    {
        List<RolDto> list = rolService.getAllRoles();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public Optional<Rol> getRolByID(@PathVariable("id") Long rolID)
    {
        Optional<Rol> rol = rolService.getRol(rolID);
        return rol;
    }

    @PostMapping("/save")
    public Rol saveRol(@Valid @RequestBody Rol rol)
    {
        return rolService.saveRol(rol);
    }

    @PutMapping("/update/{id}")
    public Rol updateRol(@RequestBody Rol rol, @PathVariable("id") Long rolID)
    {
        return rolService.updateRol(rol, rolID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRolById(@PathVariable("id") Long rolID)
    {
        rolService.deleteRolById(rolID);
        return "Deleted Successfully";
    }
}
