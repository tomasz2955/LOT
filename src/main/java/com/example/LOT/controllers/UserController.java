package com.example.LOT.controllers;

import com.example.LOT.dto.*;
import com.example.LOT.entity.User;
import com.example.LOT.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Long saveUser(@RequestBody @Valid RegisterUserDto registerUserDto) {
        return userService.saveUser(registerUserDto).getId();
    }

    @GetMapping("/{id}")
    public FindByIdResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.getUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

   @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto updateUserDto) {
        userService.updateUser(id, updateUserDto);
    }

    @PostMapping("/login")
    public LoginResponseUserDto loginUser(@RequestBody @Valid LoginUserDto loginUserDto) {
        return userService.loginUser(loginUserDto);
    }



}
