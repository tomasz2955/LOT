package com.example.LOT.controllers;


import com.example.LOT.dto.OriginAndDestinationDto;
import com.example.LOT.dto.OriginDestinationBetweenDateDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public List<Flight> findAll() {
        return flightService.getFlights();
    }

    //wyszukuje loty na podstawie Origin i Destination przy założeniu, że lot odbędzie się w bieżącym miesiącu
    @PostMapping("/search1")
    public List<Flight> findByOriginAndDestination(@RequestBody OriginAndDestinationDto originAndDestinationDto) {
        return flightService.getFlightsByOriginAndDestination(originAndDestinationDto);
    }

    //wyszukuje loty na podstawie Origin i Destination na miesiąc do przodu od dziś
    @PostMapping("/search2")
    public List<Flight> findByOriginAndDestinationAndMonth(@RequestBody OriginAndDestinationDto originAndDestinationDto) {
        return flightService.getFlightsByOriginAndDestinationAndMonth(originAndDestinationDto);
    }

    //wyszukuje loty na podstawie Origin i Destination wg przedziału dat
    @PostMapping("/search3")
    public List<Flight> findByOriginAndDestinationAndDepartureDateBetween(@RequestBody OriginDestinationBetweenDateDto originDestinationBetweenDateDto) {
        return flightService.getFlightsByOriginAndDestinationBetweenDate(originDestinationBetweenDateDto);
    }
}
