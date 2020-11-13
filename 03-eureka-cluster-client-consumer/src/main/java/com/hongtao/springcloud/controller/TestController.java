package com.hongtao.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    RestTemplate restTemplate ;
    @RequestMapping("/test")
    public String test(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://03-EUREKA-CLUSTER-CLIENT-PROVIDER/test", String.class);
        String body = forEntity.getBody();
        return "集群Consumer"+body ;
    }
}
