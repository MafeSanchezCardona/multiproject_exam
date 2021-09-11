package co.com.exam.servicemovie.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.exam.multiproject_library.utils.MessageFormat;
import co.com.exam.multiproject_library.utils.Response;
import co.com.exam.multiproject_library.utils.ResponseBuilder;

import co.com.exam.servicemovie.dto.MovieDTO;
import co.com.exam.servicemovie.entities.Movie;
import co.com.exam.servicemovie.services.MovieService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController
{
	private final MovieService movieService;

	private final ModelMapper modelMapper = new ModelMapper();
	private final ResponseBuilder builder = new ResponseBuilder();
	private final MessageFormat messageFormat = new MessageFormat();

	@PostMapping
	public Response save(@Valid @RequestBody MovieDTO movieDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return builder.failed(messageFormat.formatMessage(result));
		}

		movieService.save(convertToEntity(movieDTO));

		return builder.success(movieDTO);
	}

	@DeleteMapping("/{id}")
	public Response delete(@PathVariable("id") Long id)
	{
		Movie movie = movieService.findById(id);

		if (Objects.isNull(movie))
		{
			return builder.notFound();
		}

		String response = movieService.delete(movie);
		return builder.success(response);
	}

	@GetMapping
	public Response findAll()
	{
		List<Movie> movies = movieService.findAll();

		if (movies.isEmpty())
		{
			return builder.noContent();
		}
		return builder.success(movies.stream().map(movie -> convertToDTO(movie)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id)
	{
		Movie movie = movieService.findById(id);

		if (Objects.isNull(movie))
		{
			return builder.noContent();
		}

		return builder.success(convertToDTO(movie));
	}

	private Movie convertToEntity(@RequestBody final @Valid MovieDTO movieDTO)
	{
		return modelMapper.map(movieDTO, Movie.class);
	}

	private MovieDTO convertToDTO(final Movie movie)
	{
		return modelMapper.map(movie, MovieDTO.class);
	}
}
