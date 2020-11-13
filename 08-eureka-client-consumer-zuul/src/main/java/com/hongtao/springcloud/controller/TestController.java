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
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://08-eureka-client-provider-zuul/test", String.class);
        String body = forEntity.getBody();
        return "没有经过zuul网关,Consumer-----------"+body;
    }
    @RequestMapping("/test02")
    public String test02(String token){
        String url = "http://08-EUREKA-CLIENT-ZUUL-GATEWAY/api-zuul/test?token="+token;
        String result = restTemplate.getForObject(url,String.class);
        return "经过zuul网关,Consumer-----------"+result;
    }
}
