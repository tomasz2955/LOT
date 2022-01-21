package com.example.LOT.service;

import com.example.LOT.entity.Flight;
import com.example.LOT.repository.FlightRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlightService {

    private final FlightRepository flightRepository;


    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getFlights() {
        return flightRepository.findAll();






    }

}
