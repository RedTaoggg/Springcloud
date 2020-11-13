package com.hongtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//激活
@EnableEurekaClient
public class Provider08 {

	public static void main(String[] args) {
		SpringApplication.run(Provider08.class, args);
	}

}
