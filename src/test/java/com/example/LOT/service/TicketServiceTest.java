package com.example.LOT.service;

import com.example.LOT.FlightNotFoundException;
import com.example.LOT.TicketNotFoundException;
import com.example.LOT.UserNotFoundException;
import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.ReturnTicketDto;
import com.example.LOT.entity.*;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketServiceTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
    }

    @Test
    void shouldGetTickets() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        when(ticketRepository.findAll()).thenReturn(List.of(new Ticket(1L, new Passenger(), new Flight(), LocalDateTime.now(), "1A")));
        int result = ticketService.getTickets().size();
        assertEquals(result, 1);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of());
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenFlightNotFound() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of());
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Tomasz", "Bator",
                "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(FlightNotFoundException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenSeatIsBusy() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of(new Passenger("Tomasz", "Bator", LocalDate.now(), "1A", false)));
        Flight flight = mock(Flight.class);
        User user = mock(User.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flight.isSeatTaken("1A")).thenReturn(Boolean.TRUE);
        assertThrows(RuntimeException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenTryToDeleteNonexistentTicket() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        ReturnTicketDto returnTicketDto = new ReturnTicketDto(1L, 1L);
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TicketNotFoundException.class, () -> ticketService.deleteTicket(returnTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenTryToReturnTicketBeforeDate() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        ReturnTicketDto returnTicketDto = new ReturnTicketDto(1L, 1L);
        Flight flight = new Flight("123456", "Poland", "Germany", "Ryanair",
                LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(9), 5000.00, List.of(new Seat("1A"), new Seat("2A")));
        Passenger passenger = new Passenger(1L,"Tomasz", "Bator", LocalDate.now(), "1A", false);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(new Ticket(1L, passenger, flight, LocalDateTime.now(), "1A")));
        assertThrows(RuntimeException.class, () -> ticketService.deleteTicket(returnTicketDto));
    }

    @Test
    void shouldDeleteTicketWhenDepartureDateIdGreaterThen24Hours() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        FlightRepository flightRepository = mock(FlightRepository.class);
        TicketService ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
        ReturnTicketDto returnTicketDto = new ReturnTicketDto(1L, 1L);
        Flight flight1 = new Flight("123456", "Poland", "Germany", "Ryanair",
                LocalDateTime.now().plusHours(50), LocalDateTime.now().plusHours(55), 5000.00, List.of(new Seat("1A"), new Seat("2A")));
        Passenger passenger = new Passenger(1L,"Tomasz", "Bator", LocalDate.now(), "1A", false);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(new Ticket(1L, passenger, flight1, LocalDateTime.now(), "1A")));
        flight1.setSeatFree(1L);
        ticketRepository.deleteById(1L);
        assertNull(ticketService.deleteTicket(returnTicketDto));
    }


}
