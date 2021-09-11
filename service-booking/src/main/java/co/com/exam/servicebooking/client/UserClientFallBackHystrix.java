package co.com.exam.servicebooking.client;

import org.springframework.stereotype.Component;

import co.com.exam.multiproject_library.utils.Response;
import co.com.exam.multiproject_library.utils.ResponseBuilder;
import co.com.exam.servicebooking.model.User;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UserClientFallBackHystrix implements UserClient
{
	private final ResponseBuilder responseBuilder = new ResponseBuilder();

	@Override
	public Response findById(final Long id)
	{
		User user = User.builder().id(0L).name("").lastName("").build();
		return responseBuilder.success(user);
	}
}
