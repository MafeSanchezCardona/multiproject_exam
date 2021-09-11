package co.com.exam.servicemovie.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.exam.multiproject_library.utils.Response;


@FeignClient(name = "service-showtime")
public interface ShowtimeClient
{
	@GetMapping("/showtimes")
	Response findAll();
}
