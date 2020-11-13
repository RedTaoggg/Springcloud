package com.hongtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//用于开启Eureka注册中心服务端
@EnableEurekaServer
public class Server08 {

	public static void main(String[] args) {
		SpringApplication.run(Server08.class, args);
	}

}
