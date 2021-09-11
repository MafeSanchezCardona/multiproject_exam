package co.com.exam.servicemovie.services;

import java.util.List;

import co.com.exam.servicemovie.entities.Movie;


public interface MovieService
{
	void save(Movie movie);

	String delete(Movie movie);

	List<Movie> findAll();

	Movie findById(Long id);
}
