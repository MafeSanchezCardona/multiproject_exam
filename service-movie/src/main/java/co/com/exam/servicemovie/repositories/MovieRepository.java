package co.com.exam.servicemovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.exam.servicemovie.entities.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>
{
}
