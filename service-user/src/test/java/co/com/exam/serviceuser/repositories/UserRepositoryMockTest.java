package co.com.exam.serviceuser.repositories;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.exam.serviceuser.entities.User;


@DataJpaTest
public class UserRepositoryMockTest
{
	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenFindById_then_Return()
	{
		User user = User.builder().id(2L).name("Fernanda").lastName("Sanchez").build();

		userRepository.save(user);

		User userSearch = userRepository.findById(user.getId()).orElse(null);

		Assertions.assertThat(userSearch).isNotNull();
	}

	@Test
	public void whenFindById_then_ReturnTwoResults()
	{
		User user = User.builder().id(2L).name("Fernanda").lastName("Sanchez").build();

		userRepository.save(user);

		List<User> users = userRepository.findAll();

		Assertions.assertThat(users.size()).isEqualTo(2);
	}
}
