package com.example.LOT.entity;

import com.example.LOT.repository.FlightRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Cron {


    private final FlightRepository flightRepository;





    private final List<String> countries = new ArrayList<>(List.of("Poland", "Germany", "England", "Spain", "France", "Belgium", "Holland", "Italy", "Slovakia", "Hungary", "Croatia",
            "Malta", "Georgia", "Portugal"));

    private final List<String> airlines = new ArrayList<>(List.of("WizzAir", "Ryanair", "EasyJet", "Lufthansa", "Lot", "AirFrance"));

    public Cron(FlightRepository flightRepository) {

        this.flightRepository = flightRepository;
    }


    @Transactional
    public void createFlight() {
        Flight flight = new Flight("qwerr", countries.get((int)(Math.random()* countries.size())), countries.get((int)(Math.random()* countries.size())),
                airlines.get((int)(Math.random()* airlines.size())), LocalDateTime.now(), LocalDateTime.now(), 5000.8,
                        new ArrayList<>(List.of(new Seat("1A"), new Seat("1b"))));

        flightRepository.save(flight);
        System.out.println("locik");
    }
}
