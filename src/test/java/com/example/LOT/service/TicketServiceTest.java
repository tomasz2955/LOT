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
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    private UserRepository userRepository;
    private TicketRepository ticketRepository;
    private FlightRepository flightRepository;
    private TicketService ticketService;


    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        ticketRepository = mock(TicketRepository.class);
        flightRepository = mock(FlightRepository.class);
        ticketService = new TicketService(ticketRepository, userRepository, flightRepository);
    }

    @Test
    void shouldGetTickets() {

        when(ticketRepository.findAll()).thenReturn(List.of(new Ticket(1L, new Passenger(), new Flight(), LocalDateTime.now(), "1A")));
        int result = ticketService.getTickets().size();
        assertEquals(result, 1);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of());
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenFlightNotFound() {
        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of());
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Tomasz", "Bator",
                "bator@wp.pl", "100200300", "qwerty", new ArrayList<>())));
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(FlightNotFoundException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenSeatIsBusy() {
        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of(new Passenger("Tomasz", "Bator", LocalDate.now(), "1A", false)));
        Flight flight = mock(Flight.class);
        User user = mock(User.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flight.isSeatTaken("1A")).thenReturn(Boolean.TRUE);
        assertThrows(RuntimeException.class, () -> ticketService.buyTicket(buyingTicketDto));
    }

    @Test
    void shouldSaveTicket() {
        Passenger passenger = new Passenger("Tomasz", "Bator", LocalDate.now(), "1A", false);
        BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of(passenger));
        User user = mock(User.class);
        Flight flight = mock(Flight.class);
        Ticket ticket = new Ticket(buyingTicketDto.getUserId(), passenger, flight, LocalDateTime.now(), passenger.getSeatNumber());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        ticketRepository.save(ticket);
        when(flight.isSeatTaken("1A")).thenReturn(Boolean.FALSE);
        assertNotNull(ticketService.buyTicket(buyingTicketDto));


    }




    @Test
    void shouldDiscountOnMonday() {

            Passenger passenger = new Passenger("Tomasz", "Bator", LocalDate.now(), "1A", false);
            BuyingTicketDto buyingTicketDto = new BuyingTicketDto(1L, 1L, List.of(passenger));
            User user = new User(1L, "Tomasz", "Bator",
                    "bator@wp.pl", "100200300", "qwerty", new ArrayList<>());
            Flight flight = new Flight(1L, "123456", "Poland", "Germany", "Ryanair",
                    LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(9), 5000.00, List.of(new Seat("1A"), new Seat("2A")));
            Ticket ticket = new Ticket(1L, buyingTicketDto.getUserId(), passenger, flight, LocalDateTime.now(), passenger.getSeatNumber());

            ticketService.localDateTime = () -> LocalDateTime.parse("2022-02-21T23:59:59.999");
            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
            when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
            ticketRepository.save(ticket);
            when(flight.isSeatTaken("1A")).thenReturn(Boolean.FALSE);
            assertEquals(4000, ticketService.buyTicket(buyingTicketDto).getPrice());
        }


    @Test
    void shouldThrowExceptionWhenTryToDeleteNonexistentTicket() {

        ReturnTicketDto returnTicketDto = new ReturnTicketDto(1L, 1L);
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TicketNotFoundException.class, () -> ticketService.deleteTicket(returnTicketDto));
    }

    @Test
    void shouldThrowExceptionWhenTryToReturnTicketBeforeDate() {

        ReturnTicketDto returnTicketDto = new ReturnTicketDto(1L, 1L);
        Flight flight = new Flight("123456", "Poland", "Germany", "Ryanair",
                LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(9), 5000.00, List.of(new Seat("1A"), new Seat("2A")));
        Passenger passenger = new Passenger(1L,"Tomasz", "Bator", LocalDate.now(), "1A", false);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(new Ticket(1L, passenger, flight, LocalDateTime.now(), "1A")));
        assertThrows(RuntimeException.class, () -> ticketService.deleteTicket(returnTicketDto));
    }

    @Test
    void shouldDeleteTicketWhenDepartureDateIdGreaterThen24Hours() {

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
