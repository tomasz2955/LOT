package com.example.LOT.entity;

import com.example.LOT.repository.FlightRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
public class FlightGenerator {
Random random = new Random();

    private final FlightRepository flightRepository;


    private final List<String> countries = new ArrayList<>(List.of("Poland", "Germany", "England", "Spain", "France", "Belgium", "Holland", "Italy", "Slovakia", "Hungary", "Croatia",
            "Malta", "Georgia", "Portugal"));

    private final List<String> airlines = new ArrayList<>(List.of("WizzAir", "Ryanair", "EasyJet", "Lufthansa", "Lot", "AirFrance"));

    public FlightGenerator(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    @Scheduled(cron="*/10 * * * * MON-SUN") //chyba jak dasz gwiazdke zamiast mon-sun to bedzie oznaczac ze odpali sie kazdego dnia
    public void createFlight() {
        String origin = countries.get((int)(Math.random()* countries.size()));
        String destination = countries.get((int)(Math.random()* countries.size()));
        String airline = airlines.get((int)(Math.random()* airlines.size()));
        List<Seat> seats = List.of(new Seat("1A"), new Seat("1B"), new Seat("1C"), new Seat("1D"),
                new Seat("2A"), new Seat("2B"), new Seat("2C"), new Seat("2D"),
                new Seat("3A"), new Seat("3B"), new Seat("3C"), new Seat("3D"),
                new Seat("4A"), new Seat("4B"), new Seat("4C"), new Seat("4D"));
        //fajnie ogarnięty temat dat :)
        LocalDateTime departureDate = LocalDateTime.now().plusHours(random.nextLong(4000)); //to mi sie nie kompiluje, podkresla liczbe na czerwono
        LocalDateTime dateOfArrival = departureDate.plusHours(random.nextLong(24)); //to mi sie nie kompiluje, podkresla liczbe na czerwono
        long flightNumber = random.nextLong(1000000); //to mi sie nie kompiluje, podkresla liczbe na czerwono
        //trzebaby sprawdzic czy przypadkiem taki flight z tym id nie istnieje, mała szansa ale jest

        if(!Objects.equals(origin, destination)) { //nie musisz uzywac object equals, pisz poprostu origin.equals(destination)
            Flight flight = new Flight(Long.toString(flightNumber), origin, destination, airline, departureDate, dateOfArrival, 5000.0, seats);
            flightRepository.save(flight);
            System.out.println("creating another flight");
        } else {
            createFlight(); //bardzo sprytne, dobra robota :)
        }


    }
}
