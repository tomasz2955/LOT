package com.example.LOT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserService {
    //przydalby sie endpoint do logowania ktory moglby przy poprawnym logowaniu zwracac date jak dlugo logowanie powinno byc wazne

    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> getUserList() {
        return userRepository.findAll();
    }

    @PostMapping("/create/{user}")
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login/{user}")
    void loginUser(@RequestBody User email, @PathVariable User password) {

    }

    @DeleteMapping("/delete/{user}")
    public List<User> removeUser(@PathVariable User user) {
        userRepository.delete(user);
        return userRepository.findAll();
    }

    @PutMapping("/update/{user}")
    public List<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User currentuser = userRepository.findById(id).get();
        currentuser.setName(user.getName());
        currentuser.setLastName(user.getLastName());
        currentuser.setEmail(user.getEmail());
        currentuser.setPhoneNumber(user.getPhoneNumber());
        currentuser.setPassword(user.getPassword());
        return userRepository.findAll();
    }


}
