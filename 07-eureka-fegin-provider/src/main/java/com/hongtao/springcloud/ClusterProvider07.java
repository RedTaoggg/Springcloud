package com.hongtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClusterProvider07 {

	public static void main(String[] args) {
		SpringApplication.run(ClusterProvider07.class, args);
	}

}
