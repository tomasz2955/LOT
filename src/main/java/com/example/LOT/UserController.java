package com.example.LOT;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @PostMapping("/create/{user}")
    public void saveUser(@RequestBody User theUser) {
        userRepository.save(theUser);
    }

    @PostMapping("/login/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/update/{user}")
    public void updateUser(@RequestBody String name, String lastName, String password, String email, String phoneNumber) throws Exception {
        User currentUser = userRepository.findByEmail(email);
        if(currentUser != null) {
            currentUser.setName(name);
            currentUser.setLastName(lastName);
            currentUser.setPhoneNumber(phoneNumber);
            currentUser.setPassword(password);
        } else {
            throw new Exception("User does not exist");
        }
    }
}
