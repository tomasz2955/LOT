package com.example.LOT;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public LocalDateTime getDateAndTime (String password, String email) throws Exception {
        User currentUser1 = userRepository.findByEmail(email);
        User currentUser2 = userRepository.findByPassword(password);
        if(currentUser1 != null || currentUser2 != null) {
            return LocalDateTime.now();
        }
        throw new Exception("User does not exist");
    }

    public void saveUser(User theUser) {
        userRepository.save(theUser);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(String name, String lastName, String password, String email, String phoneNumber) throws Exception {
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
