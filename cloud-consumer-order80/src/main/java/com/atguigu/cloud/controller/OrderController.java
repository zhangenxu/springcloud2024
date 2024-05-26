package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController
{

    //public static final String PaymentSrv_URL="http://localhost:8001";
    public static final String PaymentSrv_URL="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/pay/add")
    public Result addOrder(PayDto dto){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",dto,Result.class);
    }

    @GetMapping(value = "/consumer/pay/get/{id}")
    public Result getPayInfo(@PathVariable("id") Integer id ){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/getById/"+id,Result.class,id);
    }


    @GetMapping(value = "/consumer/pay/getInfoByConsul")
    public Result getInfoByConsul(){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/getInfoByConsul",Result.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/discovery")
    public Result discovery(){
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        services.forEach(e ->{
            System.out.println(e);
        });
        System.out.println("==================================================");
        for(ServiceInstance e : instances){
            StringBuilder sb = new StringBuilder();
            sb.append(e.getServiceId()).append("\t").append(e.getHost()).append("\t").append(e.getPort()).append("\t").append(e.getUri());
            System.out.println(sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(instances.get(0).getServiceId()).append(":").append(instances.get(0).getPort());
        return Result.success(sb.toString());
    }



}
