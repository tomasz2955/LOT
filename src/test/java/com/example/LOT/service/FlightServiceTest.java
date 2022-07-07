package com.example.LOT.service;

import com.example.LOT.FlightNotFoundException;
import com.example.LOT.UserNotFoundException;
import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.OriginAndDestinationDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Seat;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import javassist.NotFoundException;
import org.assertj.core.error.OptionalShouldBeEmpty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightServiceTest {


    private FlightRepository flightRepository;
    private FlightService flightService;



    @BeforeEach
    public void setup() {

        flightRepository = mock(FlightRepository.class);
        flightService = new FlightService(flightRepository);
    }

    @Test
    void shouldThrowExceptionWhenFlightInCurrentMonthNotFound() {
        when(flightRepository.findByOriginAndDestination("Poland", "Mexico")).thenReturn(List.of(new Flight("123555", "Poland",
                        "Mexico", "Ryanair", LocalDateTime.now().plusDays(90), LocalDateTime.now().plusDays(99),
                3000.0, List.of(new Seat(1L, "b2")))));
        OriginAndDestinationDto originAndDestinationDto = new OriginAndDestinationDto("Poland", "Mexico");
        assertThrows(FlightNotFoundException.class, () -> flightService.getFlightsByOriginAndDestination(originAndDestinationDto));
    }

    @Test
    void shouldReturnFlightsWhenFlightInCurrentMonthIsPresent() {
        when(flightRepository.findByOriginAndDestination("Poland", "Mexico")).thenReturn(List.of(new Flight("123555", "Poland",
                "Mexico", "Ryanair", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3),
                3000.0, List.of(new Seat(1L, "b2")))));
        OriginAndDestinationDto originAndDestinationDto = new OriginAndDestinationDto("Poland", "Mexico");
        assertNotNull(flightService.getFlightsByOriginAndDestination(originAndDestinationDto));
    }

    @Test
    void shouldReturnFlightsWhenDateIsBetweenNowAndOneMonth() {
        when(flightRepository.findByOriginAndDestinationAndDepartureDateBetween("Poland", "Mexico", LocalDateTime.now(), LocalDateTime.now().plusMonths(1))).thenReturn(List.of(new Flight("123555", "Poland",
                "Mexico", "Ryanair", LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(11),
                3000.0, List.of(new Seat(1L, "b2")))));
        OriginAndDestinationDto originAndDestinationDto = new OriginAndDestinationDto("Poland", "Mexico");
        assertTrue(flightService.getFlightsByOriginAndDestinationAndMonth(originAndDestinationDto).isEmpty());
    }





}
