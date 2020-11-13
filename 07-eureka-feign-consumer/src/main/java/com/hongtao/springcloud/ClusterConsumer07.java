package com.hongtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableEurekaClient
//激活声明式服务消费者feign的支持
@EnableFeignClients
public class ClusterConsumer07 {

	public static void main(String[] args) {
		SpringApplication.run(ClusterConsumer07.class, args);
	}

}
