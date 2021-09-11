package co.com.exam.servicebooking.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import co.com.exam.servicebooking.client.MovieClient;
import co.com.exam.servicebooking.client.ShowtimeClient;
import co.com.exam.servicebooking.client.UserClient;
import co.com.exam.servicebooking.entities.Booking;
import co.com.exam.servicebooking.model.Movie;
import co.com.exam.servicebooking.model.Showtime;
import co.com.exam.servicebooking.model.User;
import co.com.exam.servicebooking.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService
{
	private final BookingRepository bookingRepository;
	private final MovieClient movieClient;
	private final UserClient userClient;
	private final ShowtimeClient showtimeClient;

	@Override
	public void save(final Booking booking)
	{
		bookingRepository.save(booking);
	}

	@Override
	public void delete(final Booking booking)
	{
		bookingRepository.delete(booking);
	}

	@Override
	public List<Booking> findAll()
	{
		return bookingRepository.findAll().stream().map(booking -> processBookingData(booking))
				.collect(Collectors.toList());
	}

	@Override
	public Booking findById(final Long id)
	{
		Booking booking = bookingRepository.findById(id).orElse(null);
		processBookingData(booking);
		return booking;
	}

	@Override
	public List<Booking> findByUserId(final Long userId)
	{
		return bookingRepository.findByUserId(userId).stream().map(booking -> processBookingData(booking))
				.collect(Collectors.toList());
	}

	private Booking processBookingData(final Booking booking)
	{
		if (Objects.nonNull(booking))
		{
			ModelMapper modelMapper = new ModelMapper();
			booking.setShowtime(modelMapper.map(showtimeClient.findById(booking.getShowtimeId()).getData(), Showtime.class));
			booking.setUser(modelMapper.map(userClient.findById(booking.getUserId()).getData(), User.class));

			List<String> listMovies = new ArrayList<>(Arrays.asList(booking.getMovieIds().split(",")));

			List<Movie> movies = listMovies.stream()
					.map(movieId -> modelMapper.map(movieClient.findById(Long.valueOf(movieId)).getData(), Movie.class))
					.collect(Collectors.toList());
			booking.setMovies(movies);
		}
		return booking;
	}
}
