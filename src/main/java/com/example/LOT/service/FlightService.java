package com.example.LOT.service;

import com.example.LOT.FlightNotFoundException;
import com.example.LOT.dto.*;
import com.example.LOT.entity.Flight;
import com.example.LOT.repository.FlightRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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

    //wyszukuje loty na podstawie Origin i Destination przy założeniu, że lot odbędzie się w bieżącym miesiącu
    public List<Flight> getFlightsByOriginAndDestination(OriginAndDestinationDto originAndDestinationDto) {
        List<Flight> flights = flightRepository.findByOriginAndDestination(originAndDestinationDto.getOrigin(), originAndDestinationDto.getDestination()).
                stream().filter(p->p.getDepartureDate().getMonth()==LocalDateTime.now().getMonth()).collect(Collectors.toList());;
        if(flights.isEmpty()) {
            throw new FlightNotFoundException();
        }
        return flights;
        }

    //wyszukuje loty na podstawie Origin i Destination na miesiąc do przodu od dziś
    public List<Flight> getFlightsByOriginAndDestinationAndMonth(OriginAndDestinationDto originAndDestinationDto) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime oneMonthFromNow = LocalDateTime.now().plusMonths(1);
        return flightRepository.findByOriginAndDestinationAndDepartureDateBetween
                (originAndDestinationDto.getOrigin(), originAndDestinationDto.getDestination(), today, oneMonthFromNow);
    }

    //wyszukuje loty na podstawie Origin i Destination wg przedziału dat
    public List<Flight> getFlightsByOriginAndDestinationBetweenDate(OriginDestinationBetweenDateDto originDestinationBetweenDateDto) {
        return flightRepository.findByOriginAndDestinationAndDepartureDateBetween
                (originDestinationBetweenDateDto.getOrigin(), originDestinationBetweenDateDto.getDestination(), originDestinationBetweenDateDto.getDepartureDateStart(),originDestinationBetweenDateDto.getDepartureDateEnd());
    }

    public List<String> listOfCountries() {
        return flightRepository.findAll().stream().map(Flight::getDestination).distinct().collect(Collectors.toList());
    }


}
