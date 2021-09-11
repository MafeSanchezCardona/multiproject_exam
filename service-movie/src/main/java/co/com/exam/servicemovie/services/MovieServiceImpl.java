package co.com.exam.servicemovie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import co.com.exam.servicemovie.client.BookingClient;
import co.com.exam.servicemovie.client.ShowtimeClient;
import co.com.exam.servicemovie.entities.Movie;
import co.com.exam.servicemovie.model.Booking;
import co.com.exam.servicemovie.model.Showtime;
import co.com.exam.servicemovie.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService
{
	private final MovieRepository movieRepository;
	private final BookingClient bookingClient;
	private final ShowtimeClient showtimeClient;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(final Movie movie)
	{
		movieRepository.save(movie);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String delete(final Movie movie)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		List<Booking> bookings = modelMapper.map(bookingClient.findAll().getData(), new TypeToken<ArrayList<Booking>>() {}.getType());
		List<Booking> bookingsFilter = bookings.stream().filter(booking -> booking.getMovieIds().contains(String.valueOf(movie.getId()))).collect(Collectors.toList());

		List<Showtime> showtimes = modelMapper.map(showtimeClient.findAll().getData(), new TypeToken<ArrayList<Showtime>>() {}.getType());
		List<Showtime> showtimesFilter = showtimes.stream().filter(showtime -> showtime.getMovieIds().contains(String.valueOf(movie.getId()))).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(bookingsFilter) && CollectionUtils.isEmpty(showtimesFilter)) {
			movieRepository.delete(movie);
			return "Se elimino exitosamente";
		} else {
			return "No se puede eliminar la pelicula, ya que tiene programaciones o reservas asociadas.";
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findAll()
	{
		return movieRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Movie findById(final Long id)
	{
		return movieRepository.findById(id).orElse(null);
	}
}
