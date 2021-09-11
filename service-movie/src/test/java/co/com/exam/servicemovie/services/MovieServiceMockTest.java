package co.com.exam.servicemovie.services;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.exam.servicemovie.client.BookingClient;
import co.com.exam.servicemovie.client.ShowtimeClient;
import co.com.exam.servicemovie.entities.Movie;
import co.com.exam.servicemovie.model.Showtime;
import co.com.exam.servicemovie.repositories.MovieRepository;


@SpringBootTest
public class MovieServiceMockTest
{
	@Mock
	private MovieRepository movieRepository;

	@Mock
	private BookingClient bookingClient;

	@Mock
	private ShowtimeClient showtimeClient;

	private MovieService movieService;

	@BeforeEach
	public void begin()
	{
		MockitoAnnotations.initMocks(this);
		movieService = new MovieServiceImpl(movieRepository, bookingClient, showtimeClient);
	}

	@Test
	public void when_findById_return_movie()
	{
		Mockito.when(movieRepository.findById(2L))
				.thenReturn(Optional.of(Movie.builder().id(2L).director("JKL Director").rating(4).title("Terror 2").build()));

		Movie movie = movieService.findById(2L);
		Assertions.assertThat(movie.getDirector()).isEqualTo("JKL Director");
		Assertions.assertThat(movie.getTitle()).isEqualTo("Terror 2");
	}
}
