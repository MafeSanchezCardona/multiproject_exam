package co.com.exam.servicemovie.repositories;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.exam.servicemovie.entities.Movie;


@DataJpaTest
public class MovieRepositoryMockTest
{
	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void whenFindById_then_Return()
	{
		Movie movie = Movie.builder().id(2L).director("JKL Director").rating(4).title("Terror 2").build();

		movieRepository.save(movie);

		Movie movieSearch = movieRepository.findById(movie.getId()).orElse(null);

		Assertions.assertThat(movieSearch).isNotNull();
	}

	@Test
	public void whenFindById_then_ReturnTwoResults()
	{
		Movie movie = Movie.builder().id(2L).director("JKL Director").rating(4).title("Terror 2").build();

		movieRepository.save(movie);

		List<Movie> movies = movieRepository.findAll();

		Assertions.assertThat(movies.size()).isEqualTo(2);
	}
}
