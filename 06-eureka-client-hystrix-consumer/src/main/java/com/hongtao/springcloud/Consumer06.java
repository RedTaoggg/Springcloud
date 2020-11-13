package com.hongtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//激活Hystrix的容断机制
@EnableCircuitBreaker
public class Consumer06 {

	public static void main(String[] args) {
		SpringApplication.run(Consumer06.class, args);
	}

}
