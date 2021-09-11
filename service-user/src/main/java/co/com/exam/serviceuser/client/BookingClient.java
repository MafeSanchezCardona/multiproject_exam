package co.com.exam.serviceuser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.com.exam.multiproject_library.utils.Response;


@FeignClient(name = "service-booking")
public interface BookingClient
{
	@GetMapping("/bookings/{userId}")
	Response findByUserId(@PathVariable("userId") Long userId);
}
