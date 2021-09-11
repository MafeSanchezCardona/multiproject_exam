package co.com.exam.serviceuser.services;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.exam.serviceuser.client.BookingClient;
import co.com.exam.serviceuser.entities.User;
import co.com.exam.serviceuser.model.Booking;
import co.com.exam.serviceuser.repositories.UserRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
	private final UserRepository userRepository;
	private final BookingClient bookingClient;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(final User user)
	{
		userRepository.save(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String delete(final User user)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Booking booking = modelMapper.map(bookingClient.findByUserId(user.getId()).getData(), Booking.class);

		if (Objects.nonNull(booking))
		{
			return "No se puede eliminar el usuario, ya que tiene reservas";
		}
		else
		{
			userRepository.delete(user);
			return "El usuario se elimino correctamente";
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll()
	{
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(final Long id)
	{
		return userRepository.findById(id).orElse(null);
	}
}
