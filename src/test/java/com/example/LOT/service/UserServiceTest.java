package com.example.LOT.service;

import com.example.LOT.FlightNotFoundException;
import com.example.LOT.UserNotFoundException;
import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.FindByIdResponseDto;
import com.example.LOT.dto.RegisterUserDto;
import com.example.LOT.dto.UserTicketsDto;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.error.OptionalShouldBeEmpty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.control.MappingControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository userRepository;
    private TicketRepository ticketRepository;
    private UserService userService;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        ticketRepository = mock(TicketRepository.class);
        userService = new UserService(userRepository, ticketRepository);
    }

    @Test
    void shouldGetUsers() {
        when(userService.getUsers()).thenReturn(List.of(new User("Tomasz", "Bator",
               "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));
        int result = userService.getUsers().size();
        assertEquals(result, 1);
    }
    @Test
    void shouldThrowExceptionWhenUserIsFound() { //nazwa
        //given

        when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Tomasz", "Bator",
                "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));

        //when
        FindByIdResponseDto response = userService.findById(1L);

        //then
//        assertEquals();
//        assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
    }


    @Test
    void shouldThrowExceptionWhenTicketNotFound() {

        when(ticketRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findTicketByTicketId(2L));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.findTicketByUserId(1L));

    }

    @Test
    void shouldReturnUserTicketDto() {
        User user = mock(User.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertNotNull(userService.findTicketByUserId(1L));
    }

    @Test
    void shouldThrowExceptionWhenTryToSaveExistingUser() {
        when(userRepository.existsByEmail("bator@wp.pl")).thenReturn(true);
        RegisterUserDto registerUserDto = new RegisterUserDto("Tomasz_Bator", "bator@wp.pl", "123456789", "qwertyui");
        assertThrows(RuntimeException.class, () -> userService.saveUser(registerUserDto));
    }

    @Test
    void shouldThrowExceptionWhenTryToSaveExistingUserTTT() {
        when(userRepository.existsByEmail("bator@wp.pl")).thenReturn(false);
        RegisterUserDto registerUserDto = new RegisterUserDto("Tomasz_Bator", "bator@wp.pl", "123456789", "qwertyui");
        assertThrows(RuntimeException.class, () -> userService.saveUser(registerUserDto));
        RegisterUserDto registerUserDt1 = new RegisterUserDto(null, "bator@wp.pl", "123456789", "qwertyui");
    }





}
