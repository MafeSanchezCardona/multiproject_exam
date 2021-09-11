package co.com.exam.servicebooking.client;

import org.springframework.stereotype.Component;

import co.com.exam.multiproject_library.utils.Response;
import co.com.exam.multiproject_library.utils.ResponseBuilder;
import co.com.exam.servicebooking.model.Movie;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class MovieClientFallBackHystrix implements MovieClient
{
	private final ResponseBuilder responseBuilder = new ResponseBuilder();

	@Override
	public Response findById(final Long id)
	{
		Movie movie = Movie.builder().id(0L).director("").rating(0).title("").build();
		return responseBuilder.success(movie);
	}
}
