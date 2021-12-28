package com.example.LOT;

import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
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
import java.util.Date;

@SpringBootApplication
public class LotApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final FlightRepository flightRepository;
	private final TicketRepository ticketRepository;

	public LotApplication(UserRepository userRepository, FlightRepository flightRepository, TicketRepository ticketRepository) {
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
		this.ticketRepository = ticketRepository;
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

		Flight flight1 = new Flight("WE34567", "Poland", "Germany", "Wizzair", "2021-12-20");
		Flight flight2 = new Flight("XC12001", "Italy", "Poland", "Ryanair", "2022-01-20");
		flightRepository.save(flight1);
		flightRepository.save(flight2);

		Ticket ticket1 = new Ticket(user1, flight1, "2021-12-18", 2000.45);
		Ticket ticket2 = new Ticket(user1, flight2, "2021-12-19", 823.99);
		ticketRepository.save(ticket1);
		ticketRepository.save(ticket2);
	}
}
