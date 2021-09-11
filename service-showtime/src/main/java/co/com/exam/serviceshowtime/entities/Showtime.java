package co.com.exam.serviceshowtime.entities;

import java.util.Date;
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

import org.springframework.format.annotation.DateTimeFormat;

import co.com.exam.serviceshowtime.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="showtimes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Showtime
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;

	@NotNull(message = "La fecha no debe estar vacio")
	@Column(name="date")
	private Date date;

	@Column(name="movie_ids")
	private String movieIds;

	@Transient
	private List<Movie> movies;

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Showtime showtime = (Showtime) o;
		return Objects.equals(id, showtime.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
