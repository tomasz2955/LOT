package com.example.LOT;

import com.example.LOT.entity.User;
import com.example.LOT.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LotApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public LotApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
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
		User user1 = new User("Tomasz", "Bator", "bator@wp.pl", "100200300", "qwerty");
		User user2 = new User("Piotr", "Kowalski", "kowalski@wp.pl", "4005006000", "asdfgh");
		userRepository.save(user1);
		userRepository.save(user2);
	}
}
