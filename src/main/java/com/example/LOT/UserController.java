package com.example.LOT;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class UserController {

    private final UserService userService;
    private final ModelMapper mapper= new ModelMapper();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody User theUser) {
        userService.saveUser(theUser);
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
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        User userRequest = mapper.map(updateUserDto, User.class);
        User user = userService.updateUser(id, userRequest);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/login")
    public ResponseEntity<LoginResponseUserDto> loginUser(@RequestBody LoginUserDto loginUserDto) {
        User userRequest = mapper.map(loginUserDto, User.class);
        LoginResponseUserDto user = userService.loginUser(userRequest);
        return ResponseEntity.ok().body(user);
    }



}
