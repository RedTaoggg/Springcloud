package com.hongtao.springcloud.controller;

import com.hongtao.springcloud.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String Test() {
        String str = null;
        System.out.println(str.length());
        return "使用feign的服务提供者(provider)";
    }

    @RequestMapping("/testParams01")
    public String testParams01(String name, Integer age) {
        return "testParams01使用Feign的服务提供者name:" + name + "   age:" + age;
    }

    @RequestMapping("/testParams02")
    public String testParams02(@RequestBody User user) {
        return "testParams01使用Feign的服务提供者name:" + user.getName() + "   age:" + user.getAge();
    }

    @RequestMapping("/testReturn")
    public Object testReturn() {
        User user = new User();
        user.setAge(111);
        user.setName("hhh");
        user.setId(1001L);
        return user;
        }

    @RequestMapping("/testReturnList")
    public Object testReturnList() {
        User user = new User();
        user.setAge(111);
        user.setName("ttt");
        user.setId(1001L);

        User user1 = new User();
        user1.setAge(222);
        user1.setName("aaa");
        user1.setId(1002L);

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        return list;
    }


}
