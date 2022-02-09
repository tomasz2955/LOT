package com.example.LOT;

import com.example.LOT.entity.*;
import com.example.LOT.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LotApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final FlightRepository flightRepository;


	public LotApplication(UserRepository userRepository, FlightRepository flightRepository) {
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(LotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Tomasz", "Bator", "bator@wp.pl", "100200300", "qwerty", new ArrayList<>());
		User user2 = new User("Piotr", "Kowalski", "kowalski@wp.pl", "4005006000", "asdfgh", new ArrayList<>());
		userRepository.save(user1);
		userRepository.save(user2);




		Flight flight1 = new Flight("WE34567", "Poland", "Germany", "Wizzair", LocalDateTime.now().plusHours(9), LocalDateTime.now().plusHours(13), 5000.00, new ArrayList<>(List.of(new Seat("1E"), new Seat("1F"),new Seat("1G"), new Seat("1H"))));
		Flight flight2 = new Flight("XC12001", "Italy", "Poland", "Ryanair", LocalDateTime.now().plusHours(29), LocalDateTime.now().plusHours(79), 5000.00, new ArrayList<>(List.of(new Seat("1A"), new Seat("1B"),new Seat("1C"), new Seat("1D"))));
		Flight flight3 = new Flight("XE12991", "Poland", "Germany", "Wizzair", LocalDateTime.now().plusHours(31), LocalDateTime.now().plusHours(81), 5000.00, new ArrayList<>());
		Flight flight4 = new Flight("XE12971", "Poland", "Germany", "Wizzair", LocalDateTime.now().plusMonths(3), LocalDateTime.now().plusMonths(3).plusHours(9), 5000.00, new ArrayList<>());
		Flight flight5 = new Flight("XE92971", "Poland", "Germany", "Ryanair", LocalDateTime.now().plusMonths(4), LocalDateTime.now().plusMonths(4).plusHours(9), 5000.00, new ArrayList<>());


		flightRepository.save(flight1);
		flightRepository.save(flight2);
		flightRepository.save(flight3);
		flightRepository.save(flight4);
		flightRepository.save(flight5);




	}



}
