package com.example.LOT.service;

import com.example.LOT.FlightNotFoundException;
import com.example.LOT.UserNotFoundException;
import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.FindByIdResponseDto;
import com.example.LOT.entity.*;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketServiceTest {

    @Test
    void shouldGetTickets() {
        //given
        UserRepository userRepository = mock(UserRepository.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        when(ticketRepository.findAll()).thenReturn(List.of(new Ticket(1L, new Passenger(), new Flight(), LocalDateTime.now(), "1A")));
        //when
        int result = ticketService.getTickets().size();
        //then
        assertEquals(result, 1);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        //given
        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of());
        UserRepository userRepository = mock(UserRepository.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenFlightNotFound() {
        //given
        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of());
        UserRepository userRepository = mock(UserRepository.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Tomasz", "Bator",
                "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());

        //then
        assertThrows(FlightNotFoundException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenSeatIsBusy() {
        //given

        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of(new Passenger("Tomasz", "Bator", LocalDate.now(), "1A", false)));
        UserRepository userRepository = mock(UserRepository.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        Flight flight = mock(Flight.class);

        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Tomasz", "Bator",
                "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        when(flight.isSeatTaken("1A")).thenReturn(Boolean.TRUE);

        //then
        assertThrows(RuntimeException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }
}
