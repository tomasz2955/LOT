package com.example.LOT;

import com.example.LOT.entity.Cron;
import com.example.LOT.entity.*;
import com.example.LOT.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.ArrayList;

@SpringBootApplication
@EnableScheduling
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

	Cron cron = new Cron();

	@Scheduled(cron="*/10 * * * * MON-SUN")
	public void createFlight() {
		cron.createFlight();
	}






	@Override

	public void run(String... args) throws Exception {



		User user1 = new User("Tomasz", "Bator", "bator@wp.pl", "100200300", "qwerty", new ArrayList<>());
		User user2 = new User("Piotr", "Kowalski", "kowalski@wp.pl", "4005006000", "asdfgh", new ArrayList<>());
		userRepository.save(user1);
		userRepository.save(user2);


	}



}
