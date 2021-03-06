package com.example.LOT.service;


import com.example.LOT.FlightNotFoundException;
import com.example.LOT.UserNotFoundException;
import com.example.LOT.dto.*;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final ModelMapper mapper = new ModelMapper();

    public UserService(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public FindByIdResponseDto findById(Long id) {
        User mappingUser = userRepository.findById(id)
                .orElseThrow((RuntimeException::new)); //orelse co
        return mapper.map(mappingUser, FindByIdResponseDto.class);
    }


    public Ticket findTicketByTicketId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserTicketsDto findTicketByUserId(Long id) {
        User editedUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return new UserTicketsDto(id, editedUser.getTickets());
    }


    public User saveUser(RegisterUserDto registerUserDto) {
        Boolean isPresent = userRepository.existsByEmail(registerUserDto.getEmail());
        if(!isPresent) {
            User registerUser = new User();
            registerUser.setPassword(registerUserDto.getPassword());
            registerUser.setPhoneNumber(registerUserDto.getPhoneNumber());
            registerUser.setEmail(registerUserDto.getEmail());
            //tutaj zrob test jednostkowy w kt??rym full name nie ma _ i zobacz co sie stanie
            //nast??pnie popraw logik?? tak ??eby mimo b????dnego formatu fullname aplikacja rzuci??a exception kt??ry cos
            //podpowie przegl??darce zamiast rzuca?? przypadkowo wygenerowany message
            String[] items = registerUserDto.getFullName().split("_");
            registerUser.setName(items[0]);
            registerUser.setLastName(items[1]);
            return userRepository.save(registerUser);
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
