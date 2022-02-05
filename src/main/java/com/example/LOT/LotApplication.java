package com.example.LOT;

import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Passenger;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.PassengerRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class LotApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final FlightRepository flightRepository;
	private final TicketRepository ticketRepository;
	private final PassengerRepository passengerRepository;

	public LotApplication(UserRepository userRepository, FlightRepository flightRepository, TicketRepository ticketRepository, PassengerRepository passengerRepository) {
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
		this.ticketRepository = ticketRepository;
		this.passengerRepository = passengerRepository;
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

		Passenger passenger1 = new Passenger("Anna", "Nowak", LocalDate.of(2000, 12, 12),false);
		Passenger passenger2 = new Passenger("Monika", "Kowalska", LocalDate.of(1990, 11, 10),false);
		Passenger passenger3 = new Passenger("Tomasz", "Bator", LocalDate.of(1993, 03, 17),true);



		Flight flight1 = new Flight("WE34567", "Poland", "Germany", "Wizzair", LocalDateTime.now().plusHours(9), LocalDateTime.now().plusHours(13), 5000.00, 10L);
		Flight flight2 = new Flight("XC12001", "Italy", "Poland", "Ryanair", LocalDateTime.now().plusHours(29), LocalDateTime.now().plusHours(79), 5000.00, 10L);
		Flight flight3 = new Flight("XE12991", "Poland", "Germany", "Wizzair", LocalDateTime.now().plusHours(31), LocalDateTime.now().plusHours(81), 5000.00, 10L);
		Flight flight4 = new Flight("XE12971", "Poland", "Germany", "Wizzair", LocalDateTime.now().plusMonths(3), LocalDateTime.now().plusMonths(3).plusHours(9), 5000.00, 10L);
		Flight flight5 = new Flight("XE92971", "Poland", "Germany", "Ryanair", LocalDateTime.now().plusMonths(4), LocalDateTime.now().plusMonths(4).plusHours(9), 5000.00, 10L);


		flightRepository.save(flight1);
		flightRepository.save(flight2);
		flightRepository.save(flight3);
		flightRepository.save(flight4);
		flightRepository.save(flight5);


	}


}
