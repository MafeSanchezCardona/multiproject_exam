package co.com.exam.serviceuser.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;

	@NotEmpty(message = "El nombre no debe ser vacio")
	@Column(name="name")
	private String name;

	@NotEmpty(message = "El apellido no debe ser vacio")
	@Column(name="last_name")
	private String lastName;

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final User user = (User) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
