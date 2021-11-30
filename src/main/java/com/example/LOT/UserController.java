package com.example.LOT;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody @Valid RegisterUserDto registerUserDto) {
        userService.saveUser(registerUserDto);
    }

    @GetMapping("/login/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/find")
    public List<User> findAll() {
        return userService.getUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

   @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(id, updateUserDto);
    }

    @PostMapping("/login")
    public LoginResponseUserDto loginUser(@RequestBody @Valid LoginUserDto loginUserDto) {
        return userService.loginUser(loginUserDto);
    }



}
