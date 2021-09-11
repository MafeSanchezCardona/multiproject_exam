package co.com.exam.serviceuser.services;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.exam.serviceuser.client.BookingClient;
import co.com.exam.serviceuser.entities.User;
import co.com.exam.serviceuser.repositories.UserRepository;


@SpringBootTest
public class UserServiceMockTest
{
	@Mock
	private UserRepository userRepository;

	@Mock
	private BookingClient bookingClient;

	private UserService userService;

	@BeforeEach
	public void begin()
	{
		MockitoAnnotations.initMocks(this);
		userService = new UserServiceImpl(userRepository, bookingClient);
	}

	@Test
	public void when_findById_return_user()
	{
		Mockito.when(userRepository.findById(3L))
				.thenReturn(Optional.of(User.builder().id(3L).name("Pedro").lastName("Perez").build()));

		User user = userService.findById(3L);
		Assertions.assertThat(user.getName()).isEqualTo("Pedro");
		Assertions.assertThat(user.getLastName()).isEqualTo("Perez");
	}
}
