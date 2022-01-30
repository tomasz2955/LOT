package com.example.LOT.service;


import com.example.LOT.dto.*;
import com.example.LOT.entity.User;
import com.example.LOT.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


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

    public FindByIdResponseDto findById(Long id) {
        User mappingUser = userRepository.findById(id).orElseThrow();
        return mapper.map(mappingUser, FindByIdResponseDto.class);
    }


    public UserTicketsDto findTicketById(Long id) {
        User editedUser = userRepository.findById(id).orElseThrow();
        return mapper.map(editedUser, UserTicketsDto.class);
    }


    public void saveUser(RegisterUserDto registerUserDto) {
        Boolean isPresent = userRepository.existsByEmail(registerUserDto.getEmail());
        if(!isPresent) {
            User registerUser = new User();
            registerUser.setPassword(registerUserDto.getPassword());
            registerUser.setPhoneNumber(registerUserDto.getPhoneNumber());
            registerUser.setEmail(registerUserDto.getEmail());
            //tutaj zrob test jednostkowy w którym full name nie ma _ i zobacz co sie stanie
            //następnie popraw logikę tak żeby mimo błędnego formatu fullname aplikacja rzuciła exception który cos
            //podpowie przeglądarce zamiast rzucać przypadkowo wygenerowany message
            String[] items = registerUserDto.getFullName().split("_");
            registerUser.setName(items[0]);
            registerUser.setLastName(items[1]);
            userRepository.save(registerUser);
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
        User editedUser = userRepository.findById(id).orElseThrow(); //wszedzie gdzie orElseThrow musi byc przekazany message z sensownym message
        mapper.map(updateUserDto, User.class);
        if(updateUserDto.getName() != null) {
            editedUser.setName(updateUserDto.getName());
        }
        if(updateUserDto.getLastName() != null) {
            editedUser.setLastName(updateUserDto.getLastName());
        }
        if(updateUserDto.getPhoneNumber() != null) {
            editedUser.setPhoneNumber(updateUserDto.getPhoneNumber());
        }
        if(updateUserDto.getPassword() != null) {
            editedUser.setPassword(updateUserDto.getPassword());
        }
        userRepository.save(editedUser);
    }

    @Transactional
    public LoginResponseUserDto loginUser(LoginUserDto loginUserDto) {
        User mappingUser = mapper.map(loginUserDto, User.class);
        User editedUser = userRepository.findByEmailAndPassword(mappingUser.getEmail(), mappingUser.getPassword()).orElseThrow();
        return mapper.map(editedUser, LoginResponseUserDto.class);
    }





}
