package com.hongtao.spingcloud.controller;


import com.hongtao.spingcloud.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/*
    服务提供者必须使用@RestController，服务消费者可以不使用
 */
@RestController
public class TestController {
    @Resource//注入一个Rest风格的请求模板对象
    private RestTemplate restTemplate;  //Rest模板

    @RequestMapping("/test")
    public String test() {
        /**
         * RestTemplate 是一个地基于Http协议的一个工具对象
         * 我们可以利用这个对象，以Http协议发送请求到指定的某个web服务器中
         * 在SpringCloud中可以利用这个对象来访问服务提供者
         * 这个对象可以new，也中可以交给spring来创建（建议）
         */
//    RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8081/test", String.class);
        /**
         * getForEntiry方法是一个get方法的提交请求，访问web服务器中某个请求对应着另外一个工程中的
         * @GetMapping 或@RequestMapping
         * 参数 1 需要访问具体请求路径
         * 参数 2 本次请求以后服务器返回的数据类型
         * 参数 3 为可变长度的Object类型数据，表示本次请求中的url参数数据
         *
         * 返回值：ResponseEntity对象，这个对照封装本次请求后的响应实体
         */
        System.out.println(forEntity.getStatusCode());//获取状态码 200 OK

        System.out.println(forEntity.getHeaders());//获取响应头 [Content-Type:"text/plain;charset=UTF-8", Content-Length:"35", Date:"Fri, 30 Oct 2020 01:59:51 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]

        String body = forEntity.getBody();
        return "第一个SpringCloud的服务消费者~**~~~~" + body;
    }

    @RequestMapping("/params")
    public String getForEntityParams01() {
        /**
         *请求路径中的{0}和{1}和{2}表示请求地址路径中的占位符
         * 需要后期为这些占位符动态进行赋值
         * 参数3 params 表示请求地址路径中的动态参数作用是为占位符号进行赋值类型为可变长度的Object类型
         * 注意：
         *   1、占位符中的0和1和2分别对应参数3 params数组中的索引值，用于通知Spring将数组中指定索引位置中的数据
         *     设置到对应的占位符中
         *   2、这种传递必须要清楚的知道参数数组中的每一个元素的类型以及含义因此非常容易出现传值错误参数过多时不推荐使用
         */
        //定义请求路径
        String url = " http://localhost:8081/params?id={0}&name={1}&age={2}";
        //定义请求参数数组
        Object[] params = {2L, "李四", 24};
        ResponseEntity<User> result = restTemplate.getForEntity(url, User.class, params);
        User body = result.getBody();

        return "服务消费者-----" + body;
    }

    @RequestMapping("/params02")
    public String params02() {
        String url = "http://localhost:8081/params02?id={id}&name={name}&age={age}";
        //定义请求参数map集合
        Map<String,Object> params = new HashMap<>();
        params.put("id",3L);
        params.put("name","王五");
        params.put("age",25);
        ResponseEntity<User> forEntity = restTemplate.getForEntity(url, User.class, params);
        User body = forEntity.getBody();
        return "服务消费者-----" + body;

    }
/**
 * post相关方法
 * 参数1  请求的地址路径
 * 参数2  请求的具体参数对象
 * 参数3  本次响应的具体数据类型
 * 参数4  本次请求的地址路径动态数据取值为可变长度的Object类型数据或Map集合类型数据
 *
 * 注意：
 *  参数2 取值为一个Map集合，如果是一个普通的Map集合那么value必须是一个List的泛型
 *  或者可以选择Map集合的间接子类LinkedMultiValueMap
 */
    @RequestMapping("/postForObject")
    /*
        使用postForObject 取决于Provider中@PostMapping("/...")
        如果Provider使用RequestMapping那么建议使用getForObject方法。
     */
    public String postForObject(){
        String url = "http://localhost:8081/postForObject";
        LinkedMultiValueMap params = new LinkedMultiValueMap();
        params.add("name","陈七");
        params.add("id",5L);
        params.add("age",5);
        User user = restTemplate.postForObject(url,params,User.class);
        return "服务消费者----"+user;
    }
    @RequestMapping("/put")
    public String put(){
        /*
        * put方法是以post的方式提交请求的，对应服务提供者的PutMapping
        * 参数1 具体的请求地址路径
        * 参数2 具体的请求参数对象
        * 参数3 地址栏动态参数
        * 注意：
        *   put方法主要针对于数据的修改，这个方法没有返回值不能获取响应结果，因此我们不知道本次服务是否调用成功
        * 所以除非必要情况，否则不建议使用put方法
        * */
        String url = "http://localhost:8081/put";
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("id",7L);
        map.add("name","ght");
        map.add("age",9);
        restTemplate.put(url,map);
        return "服务消费者----"+"执行了put请求";
    }
    @RequestMapping("/delete")
    public String delete(){
        String url = "http://localhost:8081/delete?id={0}";
        restTemplate.delete(url,8L);
        return "服务消费者----执行了delete请求";
    }
}
