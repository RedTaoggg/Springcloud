package com.hongtao.springcloud.controller;

import com.hongtao.springcloud.domain.User;
import com.hongtao.springcloud.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private TestService testService;
    @RequestMapping("/test")
    public String test(){
        String result = testService.test();//返回值
        return "07使用feign的服务消费者(consumer)--------------"+result;
    }
    @RequestMapping("/testParams01")
    public String testParams01(){
        String result = testService.testParams01("ght",23);//返回值
        return "07使用feign的服务消费者(consumer)--------------"+result;
    }
    @RequestMapping("/testParams02")
    public String testParams02(){
        User user = new User();
        user.setName("ggg");
        user.setAge(21);
        String result = testService.testParams02(user);//返回值
        return "07使用feign的服务消费者(consumer)--------------"+result;
    }
    @RequestMapping("/testReturn")
    public String testReturn(){
        User user = testService.testReturn();
        return "使用了Feign的服务消费者-----"+user;
    }

    @RequestMapping("/testReturnList")
    public String testReturnList(){
        List<User> list = testService.testReturnList();
        return "使用了Feign的服务消费者-----"+list;
    }
}
