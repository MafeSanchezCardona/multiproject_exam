package co.com.exam.serviceshowtime.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.exam.serviceshowtime.client.MovieClient;
import co.com.exam.serviceshowtime.entities.Showtime;
import co.com.exam.serviceshowtime.model.Movie;
import co.com.exam.serviceshowtime.repositories.ShowtimeRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService
{
	private final ShowtimeRepository showtimeRepository;

	private final MovieClient movieClient;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(final Showtime showtime)
	{
		showtimeRepository.save(showtime);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(final Showtime showtime)
	{
		showtimeRepository.delete(showtime);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Showtime> findAll()
	{
		return showtimeRepository.findAll().stream().map(showtime -> processData(showtime)).collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Showtime findById(final Long id)
	{
		Showtime showtime = showtimeRepository.findById(id).orElse(null);
		return processData(showtime);
	}

	private Showtime processData(final Showtime showtime)
	{
		if (Objects.nonNull(showtime))
		{
			ModelMapper modelMapper = new ModelMapper();
			List<String> listMovies = new ArrayList<>(Arrays.asList(showtime.getMovieIds().split(",")));

			List<Movie> movies = listMovies.stream()
					.map(movieId -> modelMapper.map(movieClient.findById(Long.valueOf(movieId)).getData(), Movie.class))
					.collect(Collectors.toList());
			showtime.setMovies(movies);
		}
		return showtime;
	}
}
