package com.example.LOT.service;

import com.example.LOT.dto.FindByIdResponseDto;

import com.example.LOT.entity.Flight;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final ModelMapper mapper = new ModelMapper();

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getFlights() {
        return flightRepository.findAll();






    }

}
