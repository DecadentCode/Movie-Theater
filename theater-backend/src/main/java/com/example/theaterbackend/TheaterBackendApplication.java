package com.example.theaterbackend;

import com.example.theaterbackend.models.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
public class TheaterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterBackendApplication.class, args);
	}

}
