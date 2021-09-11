package co.com.exam.servicebooking.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Entity
@Table(name="bookings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;

	@NotNull(message = "El usuario no debe ser vacio")
	@Column(name="user_id")
	private Long userId;

	@NotNull(message = "El showtime no debe ser vacio")
	@Column(name="showtime_id")
	private Long showtimeId;

	@Column(name="movie_ids")
	private String movieIds;

	@Transient
	private List<Movie> movies;

	@Transient
	private User user;

	@Transient
	private Showtime showtime;

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Booking booking = (Booking) o;
		return Objects.equals(id, booking.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
