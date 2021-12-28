package com.example.LOT.service;


import com.example.LOT.dto.*;
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
        return userRepository.findById(id); //tutaj orElseThrow exception i w controllerze zwrócić nie optionala a już obiekt usera. a tak nawiasem fajnie byloby zwrócić dto jak zawsze
        // (bez hasła np bo nigdy nie będzie przypadku żeby była konieczność zwracania użytkownika z jego tak prywatną informacją
    }


    public UserTicketsDto findTicketById(Long id) {
        User editedUser = userRepository.findById(id).orElseThrow(RuntimeException::new); //TODO może użyć własnego UserNotFoundException
        UserTicketsDto responseUser = mapper.map(editedUser, UserTicketsDto.class);
        return new UserTicketsDto(responseUser.getId(), responseUser.getTickets()); // czemu nie przekazesz response user? mapujesz na dokladnie ten sam obiekt z tymi samymi parametrami
    }


    public void saveUser(RegisterUserDto registerUserDto) {
        User mappingUser = mapper.map(registerUserDto, User.class); //najpierw mapujesz modelMapperem a od 47 linijki robisz mapowanie setterami. To albo jedno albo drugie proszę Pana
        boolean isPresent = userRepository.findByEmail(registerUserDto.getEmail()).isEmpty(); //tutaj orElseThrow i potem już nie musisz sprawdzać czy jest present
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
        User editedUser = userRepository.findById(id).orElseThrow(RuntimeException::new); //UserNotFoundException
        User mappingUser = mapper.map(updateUserDto, User.class); // nie rozumiem tego mapowania, po co nam mapping user? nie można użyć updateUserDto bezposrednio?
        if(mappingUser.getName() != null) {
            editedUser.setName(mappingUser.getName()); // np zamiast mappingUser.getName przekazać tutaj updateUserDto.getName(). Analogicznie tak samo
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
        if (editedUser!=null) { //nie sprawdzamy nulla działamy na optionalu  x2

            LoginResponseUserDto responseUser = mapper.map(editedUser, LoginResponseUserDto.class); //mapujesz na dto
            return new LoginResponseUserDto(LocalDateTime.now().plusHours(3), responseUser.getId()); // a tutaj mapujesz z dto na dto. Wystarczy przekazać obiekt responseUser
        } else {
            throw new RuntimeException("account does not exist");
        }
    }



}
