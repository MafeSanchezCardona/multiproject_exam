package co.com.exam.servicebooking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.com.exam.multiproject_library.utils.Response;

@FeignClient(name = "service-showtime", fallback = ShowtimeClientFallBackHystrix.class)
public interface ShowtimeClient
{
	@GetMapping("/showtimes/{id}")
	Response findById(@PathVariable("id") Long id);
}
