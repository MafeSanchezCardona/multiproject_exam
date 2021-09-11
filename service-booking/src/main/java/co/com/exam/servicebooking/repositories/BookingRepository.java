package co.com.exam.servicebooking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.exam.servicebooking.entities.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>
{
	List<Booking> findByUserId(Long userId);
}
