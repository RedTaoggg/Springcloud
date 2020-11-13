package com.hongtao.springcloud.controller;

import com.hongtao.springcloud.hystrix.MyHystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;
//    @HystrixCommand注解的的作用是标记当前控制器方法启用Hystrix的熔断机制，
//    当我们调用远程服务时如果远程服务出现了异常或超时（指定时间内没有返回响应）就会自动进行熔断
//    属性
//        fallbackMethod 用于指定一个本地方法的名称，当服务熔断以后就会调用这个方法来代替服务提供者响应信息
    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("/test")
    public String test(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://06-EUREKA-CLIENT-HYSTRIX-PROVIDER/test", String.class);
        String body = forEntity.getBody();
        return "带有容断器的服务消费者-----------"+body;
    }


    public String error(Throwable throwable){
        System.out.println(throwable.getClass());
        System.out.println(throwable.getMessage());
        return "服务熔断了"+throwable.getMessage();
    }
    @HystrixCommand(fallbackMethod = "error",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5500")}
            ,ignoreExceptions = {Exception.class}
            )
//    execution.isolation.thread.timeoutInMilliseconds表示熔断的超时时间 如果服务在指定时间内没有返回响应
//    信息则表示超时，需要熔断，默认为1000
//value用于指定属性的值1500 表示1.5秒配合timeoutInMilliseconds属性名用于自定义熔断器的超时时间
    @RequestMapping("/test02")
    public String test02(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://06-EUREKA-CLIENT-HYSTRIX-PROVIDER/test02", String.class);
        String body = forEntity.getBody();
        return "带有容断器的服务消费者-----------"+body;
    }
    @HystrixCommand(fallbackMethod = "error",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5500")},
            ignoreExceptions = {ArithmeticException.class}
    )
    @RequestMapping("/test03")
    public String test03(){
/*        String str = null;
        System.out.println(str.length());*/
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://06-EUREKA-CLIENT-HYSTRIX-PROVIDER/test03", String.class);
        String body = forEntity.getBody();
        return "带有熔断机制的服务消费者test03-----"+body;
    }
    @RequestMapping("/test04")
    public String test04(){
        String url = "http://06-EUREKA-CLIENT-HYSTRIX-PROVIDER/test04";
        MyHystrixCommand command = new MyHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")),restTemplate,url);
//     execute方法后台会自动调用run方法访问服务的提供者，如果服务提供者抛出异常则自动调用getFallback方法来返回响应数据
        String execute = (String) command.execute();
        return "带有熔断机制的服务消费者test04-----"+execute;
    }
}
