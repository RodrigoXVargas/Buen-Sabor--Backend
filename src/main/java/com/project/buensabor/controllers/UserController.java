package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.User;
import com.project.buensabor.services.UserServiceImpl;
import com.project.buensabor.services.interfaces.ProductService;
import com.project.buensabor.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/users")
public class UserController extends BaseControllerImpl<User, UserDto, UserServiceImpl> {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getEmployees")
    public ResponseEntity<?> getEmployees(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findEmployees());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
