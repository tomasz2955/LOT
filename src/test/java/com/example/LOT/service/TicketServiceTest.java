package com.example.LOT.service;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TicketServiceTest {
    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(123) => Optional.empty())

        //wywolanie metody
        ticketService.buyTicket(dto => id = 123)

        // sprawdzamy czy exception zostal wyrzucony

    }


    // except ticketRepositoryMock.save() 1 time
    // except ticketRepositoryMock.save() with parameters {}
}
