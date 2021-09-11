package co.com.exam.servicemovie.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;

	@NotEmpty(message = "El titulo no debe ser vacio")
	@Column(name="title")
	private String title;

	@NotEmpty(message = "El director no debe ser vacio")
	@Column(name="director")
	private String director;

	@Min(value = 1, message = "La clasificacion debe ser mayor o igual a 1")
	@Max(value = 5, message = "La clasificacion debe ser menor o igual a 5")
	@Column(name="rating")
	private Integer rating;

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Movie movie = (Movie) o;
		return Objects.equals(id, movie.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
