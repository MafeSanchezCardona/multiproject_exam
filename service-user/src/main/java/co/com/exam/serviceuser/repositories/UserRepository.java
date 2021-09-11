package co.com.exam.serviceuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.exam.serviceuser.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
}
