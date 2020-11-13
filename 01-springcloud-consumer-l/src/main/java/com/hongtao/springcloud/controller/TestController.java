package com.hongtao.springcloud.controller;

import com.hongtao.springcloud.domain.User;
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
    public String Tes(){
//        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<User> forEntity = restTemplate.getForEntity("http://localhost:8081/test", User.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getHeaders());
        User user = forEntity.getBody();
        return "Consumer!!!!!!!!!!!!"+user;
    }

}
