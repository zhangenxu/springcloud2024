package com.atguigu.cloud.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
@LoadBalancerClient(value = "cloud-payment-service",configuration = RestTemplate.class)
public class RestTempleteConfig {

    @Bean
    @LoadBalanced//consul天生支持负载均衡，要加一个
    public RestTemplate restTemplete(){
        return new RestTemplate();
    }

    /**
     * 将默认的轮询算法更改为随机算法：
     * https://docs.spring.io/spring-cloud-commons/reference/spring-cloud-commons/loadbalancer.html
     * @param environment
     * @param loadBalancerClientFactory
     * @return
     */
    @Bean
    ReactorLoadBalancer<ServiceInstance> ramdomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory){
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return  new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name,
                ServiceInstanceListSupplier.class),name);
    }


}
