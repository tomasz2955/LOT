package com.example.LOT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private List<User> userList = new ArrayList<>();

    public UserService() {
    }

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

    @PutMapping(path="{userid}")
        public void updateUser(
                @PathVariable("id") Long id,
                @RequestParam(required = false) String firstName,
                @RequestParam(required = false) String lastName,
                @RequestParam(required = false) String email,
                @RequestParam(required = false) String phoneNumber){
        userRepository.findById(id);
    }



    }




