package co.com.exam.serviceshowtime.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeDTO
{
	private Long id;

	@NotNull(message = "La fecha no debe estar vacio")
	private Date date;

	@NotEmpty(message = "La lista de peliculas no puede estar vacio")
	private String movieIds;

	private List<Movie> movies;
}
