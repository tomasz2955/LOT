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

    @GetMapping("/flights") // sciezek nie robimy camel case, pootwieraj strony w przeglądarce i pooglądaj jak wyglądają, np https://www.olx.pl/d/oferta/nawigacja-garmin-nuvi-3490-lmt-CID5-IDKT1Xo.html#91b4089536
    //tutaj dałbym poprostu /flights
    public List<Flight> findAll() {
        return flightService.getFlights();
    }
}
