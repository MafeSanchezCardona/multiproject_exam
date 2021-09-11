package co.com.exam.servicebooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ServiceBookingApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(ServiceBookingApplication.class, args);
	}

}
