package com.hongtao.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate template(){
        return new RestTemplate();
    }
    @Bean
    public IRule iRule(){
        //创建一个随机的Ribbon的负载均衡策略，默认为轮询策略
        return new RandomRule();//这个是随机策略
    }
}
