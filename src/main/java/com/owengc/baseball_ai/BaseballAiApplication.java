package com.owengc.baseball_ai;

import com.owengc.baseball_ai.service.PeopleLoaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BaseballAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseballAiApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(PeopleLoaderService peopleLoaderService) {
		return args -> {
			if (Arrays.asList(args).contains("--load-data")) {
				peopleLoaderService.loadPeople();
			}
		};
	}
}