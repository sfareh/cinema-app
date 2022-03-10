package com.afpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.afpa.service.CinemaInitService;

@SpringBootApplication
public class CinemaAfpaApplication implements CommandLineRunner {

	@Autowired
	CinemaInitService cinemaInitservice;

	public static void main(String[] args) {
		SpringApplication.run(CinemaAfpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		cinemaInitservice.initCities();
		cinemaInitservice.initCinemas();
		cinemaInitservice.initRooms();
		cinemaInitservice.initSeats();
		cinemaInitservice.initScreeening();
		cinemaInitservice.initCategories();
		cinemaInitservice.initMovies();
		cinemaInitservice.initProjections();
		cinemaInitservice.initClients();
		cinemaInitservice.initTickets();
	}
}
