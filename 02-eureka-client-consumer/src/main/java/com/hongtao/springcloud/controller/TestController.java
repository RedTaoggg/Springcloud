package com.hongtao.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("/test")
    public String Test(){
        /*
            通过注册中心发现服务并访问服务器
            其中02-EUREKA-CLIENT-PROVIDER就是服务在注册中心的名称（不区分大小写）
            SpringCloud会根据这个服务名到注册中心获取服务名所对应的服务所在IP+端口号
         */
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://02-EUREKA-CLIENT-PROVIDER/test", String.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getHeaders());
        String body = forEntity.getBody();
        return "Eureka,Consumer^。^"+body;
    }

}
