package co.com.exam.serviceuser.services;

import java.util.List;

import co.com.exam.serviceuser.entities.User;


public interface UserService
{
	void save(User user);

	String delete(User user);

	List<User> findAll();

	User findById(Long id);
}
