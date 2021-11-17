package com.example.LOT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class UserController {

    private final UserService userService;

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
    public List<User> findAll(@RequestBody User theUser) {
        return userService.getUsers();
    }


    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

   @PutMapping("/update/")
    public void updateUser(@RequestBody NameAndLastNameDTO nameAndLastNameDTO) throws Exception {
        User user =new User();
        user.setEmail(nameAndLastNameDTO.getEmail());
        user.setName(nameAndLastNameDTO.getName());
        user.setLastName(nameAndLastNameDTO.getLastName());
        userService.updateUser(user);

    }
}
