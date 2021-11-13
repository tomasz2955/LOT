package com.example.LOT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    private final UserService userService;

    //@Autowired adnotacja nie potrzebna, jak wstrzykujesz przez konstruktor to spring to sam ogarnia
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    void createUser(@RequestBody User user) {
        System.out.println("registering");
    }


    @GetMapping
    public List<User> getUsers() {
        return userService.getUserList();
    }
}
