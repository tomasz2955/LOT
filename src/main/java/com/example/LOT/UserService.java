package com.example.LOT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserService { //przydaloby sie pole z haslem u usera
    //przydalby sie endpoint do logowania ktory moglby przy poprawnym logowaniu zwracac date jak dlugo logowanie powinno byc wazne
    @Autowired // nie uzywamy autowired tylko wstrzykiwanie przez konstruktor
    UserRepository userRepository;
    private List<User> userList = new ArrayList<>(); // martwy kod

    @GetMapping
    List<User> getUserList() {
        return userRepository.findAll();
    }

    @PostMapping //rejestracja nie ma zadnej sciezki?
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
        User currentuser = userRepository.findById(id).get();
        currentuser.setName(user.getName());
        currentuser.setLastName(user.getLastName());
        currentuser.setEmail(user.getEmail());
        currentuser.setPhoneNumber(user.getPhoneNumber());
        return userRepository.findAll();
    }


}
