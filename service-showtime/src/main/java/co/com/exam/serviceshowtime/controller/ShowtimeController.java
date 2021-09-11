package co.com.exam.serviceshowtime.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.exam.multiproject_library.utils.MessageFormat;
import co.com.exam.multiproject_library.utils.Response;
import co.com.exam.multiproject_library.utils.ResponseBuilder;
import co.com.exam.serviceshowtime.dto.ShowtimeDTO;
import co.com.exam.serviceshowtime.entities.Showtime;
import co.com.exam.serviceshowtime.services.ShowtimeService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/showtimes")
public class ShowtimeController
{
	private final ShowtimeService showtimeService;

	private final ModelMapper modelMapper = new ModelMapper();
	private final ResponseBuilder builder = new ResponseBuilder();
	private final MessageFormat messageFormat = new MessageFormat();

	@PostMapping
	public Response save(@Valid @RequestBody ShowtimeDTO showtimeDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return builder.failed(messageFormat.formatMessage(result));
		}

		showtimeService.save(convertToEntity(showtimeDTO));

		return builder.success(showtimeDTO);
	}

	@PutMapping("/{id}")
	public Response update(@Valid @RequestBody ShowtimeDTO showtimeDTO, @PathVariable("id") Long id, BindingResult result)
	{
		if (result.hasErrors())
		{
			return builder.failed(messageFormat.formatMessage(result));
		}

		Showtime showtimeBd = showtimeService.findById(id);

		if (Objects.isNull(showtimeBd))
		{
			return builder.notFound();
		}

		showtimeBd.setDate(showtimeDTO.getDate());
		showtimeService.save(showtimeBd);

		return builder.success(showtimeDTO);
	}

	@DeleteMapping("/{id}")
	public Response delete(@PathVariable("id") Long id)
	{
		Showtime showtime = showtimeService.findById(id);

		if (Objects.isNull(showtime))
		{
			return builder.notFound();
		}

		showtimeService.delete(showtime);
		return builder.success(convertToDTO(showtime));
	}

	@GetMapping
	public Response findAll()
	{
		List<Showtime> showtimes = showtimeService.findAll();

		if (showtimes.isEmpty())
		{
			return builder.noContent();
		}
		return builder.success(showtimes.stream().map(showtime -> convertToDTO(showtime)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id)
	{
		Showtime showtime = showtimeService.findById(id);

		if (Objects.isNull(showtime))
		{
			return builder.noContent();
		}

		return builder.success(convertToDTO(showtime));
	}

	private Showtime convertToEntity(@RequestBody final @Valid ShowtimeDTO showtimeDTO)
	{
		return modelMapper.map(showtimeDTO, Showtime.class);
	}

	private ShowtimeDTO convertToDTO(final Showtime showtime)
	{
		return modelMapper.map(showtime, ShowtimeDTO.class);
	}
}
