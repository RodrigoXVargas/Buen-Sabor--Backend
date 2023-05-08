package com.project.buensabor.controllers;

import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public UserDto getUserByID(@PathVariable("id") Long userID)
    {
        UserDto user = userService.getUser(userID);
        return user;
    }

    @PostMapping("/save")
    public User saveUser(@Valid @RequestBody User user)
    {
        return userService.saveUser(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long userID)
    {
        return userService.updateUser(user, userID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long userID)
    {
        userService.deleteUserById(userID);
        return "Deleted Successfully";
    }
}
