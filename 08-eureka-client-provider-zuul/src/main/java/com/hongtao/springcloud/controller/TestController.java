package com.hongtao.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        System.out.println(10/0);
        return "使用了zuul的提供者test";
    }
    @RequestMapping("/test02")
    public String test02(){
        return "使用了zuul的提供者test02";
    }
}
