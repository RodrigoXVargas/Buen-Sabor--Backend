package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.dto.userDto.UserDtos.UserDto;
import com.project.buensabor.entities.User;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.UserServiceImpl;
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

    @PreAuthorize("hasAnyAuthority('user:getEmployees','_superAdmin')")
    @GetMapping(value = "/getEmployees")
    public ResponseEntity<?> getEmployees(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findEmployees());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:getUserRanking','_superAdmin')")
    @GetMapping(value = "/getUserRanking/{desde}&{hasta}")
    public ResponseEntity<?> getUserRanking(
            @PathVariable String desde,
            @PathVariable String hasta){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserRanking(desde, hasta));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener el ranking por fechas: "+ System.lineSeparator()+ e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('user:changeBlacklist','_superAdmin')")
    @GetMapping(value = "/changeBlacklist/{id}")
    public ResponseEntity<?> changeBlacklistUser(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.changeBlacklist(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:changeRol','_superAdmin')")
    @PutMapping(value = "/changeRol/{id}")
    public ResponseEntity<?> changeRolUser(@PathVariable Long id, @RequestBody RolDto rol){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.changeRol(rol, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('user:getAll','_superAdmin')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:getAllUsersWithoutPass','_superAdmin')")
    @GetMapping("/getAllWP")
    public ResponseEntity<?> getAllUsersWithoutPass() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersWithOutPass());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener los usuarios: "+ System.lineSeparator()+ e.getMessage());
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('user:getOne','_superAdmin')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:getUserByEmail','_superAdmin')")
    @GetMapping(value = "/getUserByEmail/{mail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String mail){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(mail));
        } catch (CustomException e) {
            return ResponseEntity.internalServerError().body("Error al buscar usuario por email: "+ System.lineSeparator()+ e.getMessage());
        }
    }


    @PreAuthorize("hasAnyAuthority('user:save','_superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody UserDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:update','_superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody UserDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('user:delete','_superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
