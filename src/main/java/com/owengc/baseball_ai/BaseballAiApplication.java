package com.owengc.baseball_ai;

import com.owengc.baseball_ai.service.*;
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
	public CommandLineRunner dataLoader(PeopleLoaderService peopleLoaderService,
										TeamsLoaderService teamsLoaderService,
										BattingLoaderService battingLoaderService,
										PitchingLoaderService pitchingLoaderService,
										FieldingLoaderService fieldingLoaderService
	) {
		return args -> {
			if (Arrays.asList(args).contains("--load-people")) {
				peopleLoaderService.loadPeople();
			}
			if (Arrays.asList(args).contains("--load-teams")) {
				teamsLoaderService.loadTeams();
			}
			if (Arrays.asList(args).contains("--load-batting")) {
				battingLoaderService.loadBatting();
			}
			if (Arrays.asList(args).contains("--load-pitching")) {
				pitchingLoaderService.loadPitching();
			}
			if (Arrays.asList(args).contains("--load-fielding")) {
				fieldingLoaderService.loadFielding();
			}
		};
	}
}