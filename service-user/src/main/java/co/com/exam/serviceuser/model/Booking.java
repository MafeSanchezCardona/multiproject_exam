package co.com.exam.serviceuser.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking
{
	private Long id;
	private Long userId;
	private Long showtimeId;
	private List<Long> movieIds;
}
