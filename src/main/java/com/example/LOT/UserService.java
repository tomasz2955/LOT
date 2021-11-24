package com.example.LOT;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();


    public UserService(UserRepository theUserRepository) {
        this.userRepository = theUserRepository;
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


    @Transactional
    public User updateUser(Long id, User theUser) {
        User editedUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        editedUser.setName(theUser.getName());
        editedUser.setLastName(theUser.getLastName());
        editedUser.setPhoneNumber(theUser.getPhoneNumber());
        editedUser.setPassword(theUser.getPassword());
        return userRepository.save(editedUser);
    }

    @Transactional
    public LoginResponseUserDto loginUser(User theUser) {
        LoginUserDto userRequest = mapper.map(theUser, LoginUserDto.class);
        User userr = userRepository.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
        if (userr == null) {
            throw new RuntimeException();
        }
        return new LoginResponseUserDto(LocalDateTime.now().plusHours(3), userr.getId());
    }



}
