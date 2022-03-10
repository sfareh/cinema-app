package com.afpa.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.dao.MovieRepository;
import com.afpa.entities.Movie;
import com.afpa.entities.Ticket;

import lombok.Data;

@RestController
public class CinemaRestController {
	
	@Autowired
	MovieRepository movieRepository;
	
	@GetMapping(path="/poster/{id}", produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable(name="id") Long id) throws IOException {
		
		Movie movie = movieRepository.findById(id).get();
		String posterName = movie.getPoster();
		System.out.println("NOM IMAGE : " + posterName);
		File file = new File(System.getProperty("user.dir") + "/src/main/resources/static/images/" + posterName);
		System.out.println("NOM FILE : " + file);
		Path path = Paths.get(file.toURI());
		System.out.println("NOM PATH : " + path);
		return Files.readAllBytes(path);
	}
	
	@PostMapping("/payedTickets/{id}")
	public List<Ticket> payedTickets(@RequestBody TicketIdList ticketIdList) {
		 List<Ticket> ticket = new ArrayList<>();
		
		return ticket;
	}
}

//getters - setters
@Data
class TicketIdList{
	private String clientName;
	private List<Long> tickets = new ArrayList<>();
}
