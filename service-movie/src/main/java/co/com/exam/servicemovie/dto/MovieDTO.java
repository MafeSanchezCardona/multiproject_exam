package co.com.exam.servicemovie.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO
{
	private Long id;

	@NotEmpty(message = "El titulo no debe ser vacio")
	private String title;

	@NotEmpty(message = "El director no debe ser vacio")
	private String director;

	@Min(value = 1, message = "La clasificacion debe ser mayor o igual a 1")
	@Max(value = 5, message = "La clasificacion debe ser menor o igual a 5")
	private Integer rating;
}
