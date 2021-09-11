package co.com.exam.servicebooking.services;

import java.util.List;

import co.com.exam.servicebooking.entities.Booking;


public interface BookingService
{
	void save(Booking booking);

	void delete(Booking booking);

	List<Booking> findAll();

	Booking findById(Long id);

	List<Booking> findByUserId(Long userId);
}
