package co.com.exam.servicebooking.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import co.com.exam.servicebooking.model.Movie;
import co.com.exam.servicebooking.model.Showtime;
import co.com.exam.servicebooking.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO
{
	private Long id;

	@NotNull(message = "El usuario no debe ser vacio")
	private Long userId;

	@NotNull(message = "El showtime no debe ser vacio")
	private Long showtimeId;

	private String movieIds;
	private List<Movie> movies;
	private User user;
	private Showtime showtime;
}
