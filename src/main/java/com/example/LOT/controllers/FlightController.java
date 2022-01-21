package com.example.LOT.controllers;


import com.example.LOT.entity.Flight;
import com.example.LOT.service.FlightService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FlightController {
    private final FlightService flightService;
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public List<Flight> findAll() {
        return flightService.getFlights();
    }

    //potrzebny endpoint do wyszukiwania lotów po origin i destination
    // oraz przedział czasowy (from,to) i musisz zwrócić loty w w tym kierunku które są w tym przedziale czasowym
    //https://www.baeldung.com/spring-data-jpa-query-by-date
    //https://stackoverflow.com/questions/39784344/check-date-between-two-other-dates-spring-data-jpa
}
