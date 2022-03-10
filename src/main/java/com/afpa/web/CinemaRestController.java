package com.afpa.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.dao.MovieRepository;
import com.afpa.dao.TicketRepository;
import com.afpa.entities.Movie;
import com.afpa.entities.Ticket;

import lombok.Data;

@RestController
public class CinemaRestController {
	
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	TicketRepository ticketRepository;
	
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
	
	@PostMapping("/payedTickets")
	@Transactional
	public List<Ticket> payedTickets(@RequestBody TicketIdList ticketIdList) {
		 List<Ticket> ticketList = new ArrayList<Ticket>();
		 
		 ticketIdList.getTickets().forEach(id -> {
			 Ticket ticket = ticketRepository.findById(id).get();
			 ticket.setClientName(ticketIdList.getName());
			 ticket.setReserved(true);
			 ticketRepository.save(ticket);
			 ticketList.add(ticket);
		 });
		
		return ticketList;
	}
}

//getters - setters
@Data
class TicketIdList{
	private String name;
	private List<Long> tickets = new ArrayList<>();
}
