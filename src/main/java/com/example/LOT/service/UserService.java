package com.example.LOT.service;


import com.example.LOT.dto.LoginResponseUserDto;
import com.example.LOT.dto.LoginUserDto;
import com.example.LOT.dto.RegisterUserDto;
import com.example.LOT.dto.UpdateUserDto;
import com.example.LOT.entity.User;
import com.example.LOT.repository.UserRepository;
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

    public Optional<User> findTicketById(Long id) {
        boolean isPresent = userRepository.findById(id).isEmpty();
        if(isPresent) {
            return userRepository.findById(id).se
        }

    }


    public void saveUser(RegisterUserDto registerUserDto) {
        User mappingUser = mapper.map(registerUserDto, User.class);
        boolean isPresent = userRepository.findByEmail(registerUserDto.getEmail()).isEmpty();
        if(isPresent) {
            mappingUser.setPassword(registerUserDto.getPassword());
            mappingUser.setPhoneNumber(registerUserDto.getPhoneNumber());
            mappingUser.setEmail(registerUserDto.getEmail());
            String[] items = registerUserDto.getFullName().split("_");
            mappingUser.setName(items[0]);
            mappingUser.setLastName(items[1]);
            userRepository.save(mappingUser);
        }
        else {
            throw new RuntimeException("mail exists");
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    @Transactional
    public void updateUser(Long id, UpdateUserDto updateUserDto) {
        User editedUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        User mappingUser = mapper.map(updateUserDto, User.class);
        if(mappingUser.getName() != null) {
            editedUser.setName(mappingUser.getName());
        }
        if(mappingUser.getLastName() != null) {
            editedUser.setLastName(mappingUser.getLastName());
        }
        if(mappingUser.getPhoneNumber() != null) {
            editedUser.setPhoneNumber(mappingUser.getPhoneNumber());
        }
        if(mappingUser.getPassword() != null) {
            editedUser.setPassword(mappingUser.getPassword());
        }
        userRepository.save(editedUser);
    }

    @Transactional
    public LoginResponseUserDto loginUser(LoginUserDto loginUserDto) {
        User mappingUser = mapper.map(loginUserDto, User.class);
        User editedUser = userRepository.findByEmailAndPassword(mappingUser.getEmail(), mappingUser.getPassword());
        if (editedUser!=null) { //nie sprawdzamy nulla działamy na optionalu

            LoginResponseUserDto responseUser = mapper.map(editedUser, LoginResponseUserDto.class);
            return new LoginResponseUserDto(LocalDateTime.now().plusHours(3), responseUser.getId());
        } else {
            throw new RuntimeException("account does not exist");
        }
    }



}
