package com.example.LOT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserService {

    @Autowired
    UserRepository userRepository;
    private List<User> userList = new ArrayList<>();

    @GetMapping
    List<User> getUserList() {
        return userRepository.findAll();
    }

    @PostMapping
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/delete/{user}")
    public List<User> removeUser(@PathVariable User user) {
        userRepository.delete(user);
        return userRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public List<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User currentuser = userRepository.findById(id);
        currentuser.setName(user.getName());
        currentuser.setLastName(user.getLastName());
        currentuser.setEmail(user.getEmail());
        currentuser.setPhoneNumber(user.getPhoneNumber());
        return userRepository.findAll();
    }


}
