package com.hongtao.springcloud.controller;

import com.hongtao.springcloud.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @RequestMapping("/test")
    public User test (){
        User user = new User();
        user.setId(1001);
        user.setName("ght");
        user.setAge(10);
        return user;
    }
    @RequestMapping("/params")
    public Object params (User user){
        user.setName(user.getName()+"服务提供者");
        return user;
    }
    @RequestMapping("/params02")
    public Object params02 (User user){
        user.setName(user.getName()+"服务提供者map");
        return user;
    }
    @PostMapping("/postForObject")
    public Object postForObject(User user){
        user.setName(user.getName()+"服务提供者");
        return user;
    }
    @PutMapping("/put")
    public void put(User user){
        user.setName(user.getName()+"服务提供者");
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }
    @DeleteMapping("/delete")
    public Object delete(Integer id){
        System.out.println("id-----:"+id);
        return "删除成功";
    }
}
