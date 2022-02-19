package com.example.LOT.service;

import com.example.LOT.UserNotFoundException;
import com.example.LOT.entity.User;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.error.OptionalShouldBeEmpty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.control.MappingControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    void shouldGetUsers() {
        UserRepository userRepository = mock(UserRepository.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        UserService userService = new UserService(userRepository, ticketRepository);
        when(userService.getUsers()).thenReturn(List.of(new User("Tomasz", "Bator",
                "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));
        int result = userService.getUsers().size();
        assertEquals(result, 1);
    }
    @Test
    void shouldThrowExceptionWhenUserIsFound() {
        UserRepository userRepository = mock(UserRepository.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        UserService userService = new UserService(userRepository, ticketRepository);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Tomasz", "Bator",
                "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));
        assertThrows(UserNotFoundException.class, ()->userService.findById(1L));
    }


    @Test
    void shouldThrowExceptionWhenTicketNotFound() {
        UserRepository userRepository = mock(UserRepository.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        UserService userService = new UserService(userRepository, ticketRepository);
        when(ticketRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, ()->userService.findTicketByTicketId(2L));
    }


}
