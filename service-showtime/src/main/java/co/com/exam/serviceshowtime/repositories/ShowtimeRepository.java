package co.com.exam.serviceshowtime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.exam.serviceshowtime.entities.Showtime;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long>
{
}
