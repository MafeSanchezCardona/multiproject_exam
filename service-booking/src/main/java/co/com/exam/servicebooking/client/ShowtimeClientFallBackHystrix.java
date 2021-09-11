package co.com.exam.servicebooking.client;

import org.springframework.stereotype.Component;

import co.com.exam.multiproject_library.utils.Response;
import co.com.exam.multiproject_library.utils.ResponseBuilder;
import co.com.exam.servicebooking.model.Showtime;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class ShowtimeClientFallBackHystrix implements ShowtimeClient
{
	private final ResponseBuilder responseBuilder = new ResponseBuilder();

	@Override
	public Response findById(final Long id)
	{
		Showtime showtime = Showtime.builder().id(0L).date(null).build();
		return responseBuilder.success(showtime);
	}
}
