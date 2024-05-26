package com.atguigu.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempleteConfig {

    @Bean
    @LoadBalanced//consul天生支持负载均衡，要加一个
    public RestTemplate restTemplete(){
        return new RestTemplate();
    }


}
