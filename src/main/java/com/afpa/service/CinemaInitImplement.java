package com.afpa.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.dao.CategoryRepository;
import com.afpa.dao.CinemaRepository;
import com.afpa.dao.CityRepository;
import com.afpa.dao.ClientRepository;
import com.afpa.dao.MovieRepository;
import com.afpa.dao.ProjectionRepository;
import com.afpa.dao.RoomRepository;
import com.afpa.dao.ScreeningRepository;
import com.afpa.dao.SeatRepository;
import com.afpa.dao.TicketRepository;
import com.afpa.entities.Category;
import com.afpa.entities.Cinema;
import com.afpa.entities.City;
import com.afpa.entities.Client;
import com.afpa.entities.Movie;
import com.afpa.entities.Projection;
import com.afpa.entities.Room;
import com.afpa.entities.Screening;
import com.afpa.entities.Seat;
import com.afpa.entities.Ticket;

@Service
@Transactional 
public class CinemaInitImplement implements CinemaInitService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CinemaRepository cinemaRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ProjectionRepository projectionRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ScreeningRepository screeningRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public void initCategories() {

		Stream.of("Action", "Fiction", "Comedy", "Drama", "Adam", "Sarah", "Austin", "Sydney").forEach(c -> {
			Category category = new Category();
			category.setName(c);
			categoryRepository.save(category);
		});
	}

	@Override
	public void initCinemas() {

		cityRepository.findAll().forEach(v -> {
			Stream.of("Bexley", "Century", "Dundas", "Fairmont", "Royale").forEach(c -> {
				Cinema cinema = new Cinema();
				cinema.setCity(v);
				cinema.setName(c);
				cinema.setNumberOfRooms(3 + new Random().nextInt(3));
				cinemaRepository.save(cinema);
			});
		});

	}

	@Override
	public void initCities() {

		Stream.of("Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Montpellier", "Strasbourg")
				.forEach(c -> {
					City city = new City();
					city.setName(c);
					cityRepository.save(city);
				});
	}

	@Override
	public void initClients() {

		Stream.of("Léo", "Chloé", "Gabriel", "Aliayh", "Adam", "Sarah", "Austin", "Sydney").forEach(c -> {
			Client client = new Client();
			client.setFirstname(c);
			clientRepository.save(client);
		});
	}

	@Override
	public void initMovies() {

		double[] time = new double[] { 1.5, 2., 2.5, 3.0 };
		List<Category> categories = categoryRepository.findAll();

		Stream.of("X Men", " Deadpool", "Iron Man", " Captain America", "Captain Marvel", "Thor").forEach(m -> {
			Movie movie = new Movie();
			movie.setName(m);
			movie.setCategory(categories.get(new Random().nextInt(categories.size())));
			movie.setLength(time[new Random().nextInt(time.length)]);
			movie.setPoster(m.toLowerCase().replaceAll(" ", "") + ".jpeg");
			movie.setFilm_maker("Marvel");
			movieRepository.save(movie);
		});
	}

	@Override
	public void initProjections() {

		cityRepository.findAll().forEach(c -> {
			c.getCinemas().forEach(cine -> {
				cine.getRooms().forEach(r -> {
					movieRepository.findAll().forEach(m -> {
						screeningRepository.findAll().forEach(s -> {
							Projection projection = new Projection();
							projection.setProjectionDate(new Date());
							projection.setMovie(m);
							projection.setPrice(15.5);
							projection.setRoom(r);
							projection.setScreening(s);
							projectionRepository.save(projection);
						});
					});
				});
			});
		});

	}

	@Override
	public void initRooms() {

		cinemaRepository.findAll().forEach(c -> {

			for (int i = 0; i < c.getNumberOfRooms(); i++) {
				Room room = new Room();
				room.setCinema(c);
				room.setNumberOfSeats(20 + new Random().nextInt(10));
				room.setName("Room " + (i + 1));

				roomRepository.save(room);
			}
		});
	}

	@Override
	public void initScreeening() {

		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Stream.of("10:00", "13:00", "16:00", "19:00", "22:00").forEach(s -> {
			Screening screening = new Screening();

			try {
				screening.setStart_time(dateFormat.parse(s));
			} catch (Exception e) {
				e.printStackTrace();
			}

			screeningRepository.save(screening);
		});
	}

	@Override
	public void initSeats() {

		roomRepository.findAll().forEach(s -> {
			for (int i = 0; i < s.getNumberOfSeats(); i++) {
				Seat seat = new Seat();
				seat.setRoom(s);
				seat.setSeatNumber(i + 1);

				seatRepository.save(seat);
			}
		});
	}

	@Override
	public void initTickets() {

		projectionRepository.findAll().forEach(p -> {
			p.getRoom().getSeat().forEach(s -> {
				Ticket ticket = new Ticket();
				ticket.setPrice(p.getPrice());
				ticket.setSeat(s);
				ticket.setProjection(p);
				ticket.setReserved(false);
				ticketRepository.save(ticket);
			});
		});
	}

}
