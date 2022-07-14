package com.example.LOT;

import com.example.LOT.entity.FlightGenerator;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Profile("!test")
public class DataGenerator implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    public DataGenerator(UserRepository userRepository, FlightRepository flightRepository) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        FlightGenerator flightGenerator = new FlightGenerator(flightRepository);
        flightGenerator.createFlight();

        User user1 = new User("Tomasz", "Bator", "bator@wp.pl", "100200300", "qwerty", new ArrayList<>());
        User user2 = new User("Piotr", "Kowalski", "kowalski@wp.pl", "4005006000", "asdfgh", new ArrayList<>());
        userRepository.save(user1);
        userRepository.save(user2);



    }
}
