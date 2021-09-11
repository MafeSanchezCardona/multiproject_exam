package co.com.exam.servicebooking.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.util.CollectionUtils;
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
import co.com.exam.servicebooking.dto.BookingDTO;
import co.com.exam.servicebooking.entities.Booking;
import co.com.exam.servicebooking.services.BookingService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController
{
	private final BookingService bookingService;

	private final ModelMapper modelMapper = new ModelMapper();
	private final ResponseBuilder builder = new ResponseBuilder();
	private final MessageFormat messageFormat = new MessageFormat();

	@PostMapping
	public Response save(@Valid @RequestBody BookingDTO bookingDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return builder.failed(messageFormat.formatMessage(result));
		}

		bookingService.save(convertToEntity(bookingDTO));

		return builder.success(bookingDTO);
	}

	@DeleteMapping("/{id}")
	public Response delete(@PathVariable("id") Long id)
	{
		Booking booking = bookingService.findById(id);

		if (Objects.isNull(booking))
		{
			return builder.notFound();
		}

		bookingService.delete(booking);
		return builder.success(convertToDTO(booking));
	}

	@GetMapping
	public Response findAll()
	{
		List<Booking> bookings = bookingService.findAll();

		if (bookings.isEmpty())
		{
			return builder.noContent();
		}
		return builder.success(bookings.stream().map(booking -> convertToDTO(booking)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id)
	{
		Booking booking = bookingService.findById(id);

		if (Objects.isNull(booking))
		{
			return builder.noContent();
		}

		return builder.success(convertToDTO(booking));
	}

	@GetMapping("/user/{userId}")
	public Response findByUserId(@PathVariable("userId") Long userId)
	{
		List<Booking> bookings = bookingService.findByUserId(userId);

		if (CollectionUtils.isEmpty(bookings))
		{
			return builder.noContent();
		}

		return builder.success(bookings.stream().map(booking -> convertToDTO(booking)).collect(Collectors.toList()));
	}

	private Booking convertToEntity(@RequestBody final @Valid BookingDTO bookingDTO)
	{
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper.map(bookingDTO, Booking.class);
	}

	private BookingDTO convertToDTO(final Booking booking)
	{
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper.map(booking, BookingDTO.class);
	}
}
