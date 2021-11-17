package com.example.LOT;

import com.example.LOT.User;
import com.example.LOT.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void saveUser(User theUser) {
        userRepository.save(theUser);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User theUSer) throws Exception {
        boolean result = userRepository.findByEmail(theUSer.getEmail());
        if (result = true) {
            userRepository.save(theUSer);
        } else {
            throw new Exception("email is not valid");
        }
    }
    }
