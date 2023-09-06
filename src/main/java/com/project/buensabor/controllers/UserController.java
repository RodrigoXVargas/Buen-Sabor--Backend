package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.User;
import com.project.buensabor.services.UserServiceImpl;
import com.project.buensabor.services.interfaces.ProductService;
import com.project.buensabor.services.interfaces.StatusService;
import com.project.buensabor.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/users")
public class UserController extends BaseControllerImpl<User, UserDto, UserServiceImpl> {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('user:getEmployees','superAdmin')")
    @GetMapping(value = "/getEmployees")
    public ResponseEntity<?> getEmployees(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findEmployees());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:changeBlacklist','superAdmin')")
    @GetMapping(value = "/changeBlacklist/{id}")
    public ResponseEntity<?> changeBlacklistUser(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.changeBlacklist(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:changeRol','superAdmin')")
    @PutMapping(value = "/changeRol/{id}")
    public ResponseEntity<?> changeRolUser(@PathVariable Long id, @RequestBody RolDto rol){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.changeRol(rol, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('user:getAll','superAdmin')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('user:getOne','superAdmin')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:getUserByEmail','superAdmin')")
    @GetMapping(value = "/getUserByEmail/{mail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String mail){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(mail));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Usuario no encontrado o peticion invalida.\"}");
        }
    }


    @PreAuthorize("hasAnyAuthority('user:save','superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody UserDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:update','superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody UserDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:delete','superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
