package co.com.exam.serviceshowtime.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.com.exam.multiproject_library.utils.Response;


@FeignClient(name = "service-movie")
public interface MovieClient
{
	@GetMapping("/movies/{id}")
	Response findById(@PathVariable("id") Long id);
}
