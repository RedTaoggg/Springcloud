package com.hongtao.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String Test(){
        return "使用Eureka集群的服务提供者(provider)";
    }
}
