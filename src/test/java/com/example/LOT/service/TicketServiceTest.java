package com.example.LOT.service;

import com.example.LOT.entity.User;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketServiceTest {

    @Test
    void shouldThrowExceptionWhenTicketNotFound() {
        UserRepository userRepository = mock(UserRepository.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        UserService userService = new UserService(userRepository, ticketRepository);
        when(userService.getUsers()).thenReturn(List.of(new User("Tomasz", "Bator",
                "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));
        int result = userService.getUsers().size();
        assertEquals(result, 1);
    }



   /*

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(123) => Optional.empty())

        //wywolanie metody
        ticketService.buyTicket(dto => id = 123)

        // sprawdzamy czy exception zostal wyrzucony

    }


    // except ticketRepositoryMock.save() 1 time
    // except ticketRepositoryMock.save() with parameters {}


    */
}
