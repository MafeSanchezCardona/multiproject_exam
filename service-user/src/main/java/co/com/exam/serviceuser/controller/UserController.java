package co.com.exam.serviceuser.controller;

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
import co.com.exam.serviceuser.dto.UserDTO;
import co.com.exam.serviceuser.entities.User;
import co.com.exam.serviceuser.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController
{
	private final UserService userService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final ResponseBuilder builder = new ResponseBuilder();
	private final MessageFormat messageFormat = new MessageFormat();

	@PostMapping
	public Response save(@Valid @RequestBody UserDTO userDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return builder.failed(messageFormat.formatMessage(result));
		}

		userService.save(convertToEntity(userDTO));

		return builder.success(userDTO);
	}

	@DeleteMapping("/{id}")
	public Response delete(@PathVariable("id") Long id)
	{
		User user = userService.findById(id);

		if (Objects.isNull(user))
		{
			return builder.notFound();
		}

		String response = userService.delete(user);
		return builder.success(response);
	}

	@GetMapping
	public Response findAll()
	{
		List<User> users = userService.findAll();

		if (users.isEmpty())
		{
			return builder.noContent();
		}
		return builder.success(users.stream().map(user -> convertToDTO(user)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id)
	{
		User user = userService.findById(id);

		if (Objects.isNull(user))
		{
			return builder.noContent();
		}

		return builder.success(convertToDTO(user));
	}

	private User convertToEntity(@RequestBody final @Valid UserDTO userDTO)
	{
		return modelMapper.map(userDTO, User.class);
	}

	private UserDTO convertToDTO(final User user)
	{
		return modelMapper.map(user, UserDTO.class);
	}
}
