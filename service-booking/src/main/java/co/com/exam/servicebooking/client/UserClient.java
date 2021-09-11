package co.com.exam.servicebooking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.com.exam.multiproject_library.utils.Response;

@FeignClient(name = "service-user", fallback = UserClientFallBackHystrix.class, url = "localhost:9060")
public interface UserClient
{
	@GetMapping("/users/{id}")
	Response findById(@PathVariable("id") Long id);
}
