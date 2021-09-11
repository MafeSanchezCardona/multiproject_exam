package co.com.exam.servicemovie.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import co.com.exam.multiproject_library.utils.Response;


@FeignClient(name = "service-booking")
public interface BookingClient
{
	@GetMapping("/bookings")
	Response findAll();
}
