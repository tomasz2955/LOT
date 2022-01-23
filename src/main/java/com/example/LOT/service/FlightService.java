package com.example.LOT.service;

import com.example.LOT.dto.OriginAndDestinationDto;
import com.example.LOT.dto.OriginDestinationBetweenDateDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.repository.FlightRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FlightService {

    private final FlightRepository flightRepository;


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
            throw new RuntimeException("Flights not found");
        }
        return flights;
        }

    //wyszukuje loty na podstawie Origin i Destination na miesiąc do przodu od dziś
    public List<Flight> getFlightsByOriginAndDestinationAndMonth(OriginAndDestinationDto originAndDestinationDto) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime oneMonthFromNow = LocalDateTime.now().plusMonths(1); //warto przypisywac rzeczy do nazw, latwiej sie wtedy czyta kod gdzie jest to uzyte
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureDateBetween
                (originAndDestinationDto.getOrigin(), originAndDestinationDto.getDestination(), today, oneMonthFromNow);
        if(flights.isEmpty()) {
            throw new RuntimeException("Flights not found"); //tutaj nie rzucalbym exceptiona, przekazalbym pustą listę. Tutaj akurat
            //brak lotów nie jest błędem, to jest poprostu stan rzeczy także exceptiona nie rzucamy
        }
        return flights;
    }

    //wyszukuje loty na podstawie Origin i Destination wg przedziału dat
    public List<Flight> getFlightsByOriginAndDestinationBetweenDate(OriginDestinationBetweenDateDto originDestinationBetweenDateDto) {
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureDateBetween
                (originDestinationBetweenDateDto.getOrigin(), originDestinationBetweenDateDto.getDestination(), originDestinationBetweenDateDto.getDepartureDateStart(),originDestinationBetweenDateDto.getDepartureDateEnd());
        if(flights.isEmpty()) {
            throw new RuntimeException("Flights not found"); //jak wyzej
        }
        return flights;
    }
}
