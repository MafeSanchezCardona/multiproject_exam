package co.com.exam.serviceshowtime.services;

import java.util.List;

import co.com.exam.serviceshowtime.entities.Showtime;


public interface ShowtimeService
{
	void save(Showtime showtime);

	void delete(Showtime showtime);

	List<Showtime> findAll();

	Showtime findById(Long id);
}
