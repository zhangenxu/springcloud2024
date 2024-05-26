package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.resp.Result;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service",fallbackMethod = "myCircuitFallback")
    public Result<String> myCircuitBeraker(@PathVariable("id") Integer id){
        return payFeignApi.myCircuit(id);
    }

    public Result<String> myCircuitFallback(Integer id, Throwable t){
        return Result.fail("500","myCircuitFallback,系统繁忙，请稍后再试"+id);
    }


    /**
     * 舱壁隔离：SEMAPHORE（信号量）
     * @param id
     * @return
     */
//    @GetMapping(value = "/feign/pay/bulkhead/{id}")
//    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
//    public Result<String> myBulkhead(@PathVariable("id") Integer id){
//        return payFeignApi.myBulkhead(id);
//    }
//
//    public Result<String> myBulkheadFallback(Integer id, Throwable t){
//        return Result.fail("500","myBulkheadFallback,系统繁忙，请稍后再试"+id);
//    }

    /**
     * 舱壁隔离：threadPool
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadPool(@PathVariable("id") Integer id){
        System.out.println(Thread.currentThread().getName()+"\t"+"--开始进入");
         try {
             TimeUnit.SECONDS.sleep(3);
         }catch (InterruptedException e){
             e.printStackTrace();
         }
        System.out.println(Thread.currentThread().getName()+"\t"+"--准备离开");

        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id)+"\t"+" ");

    }

    public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable t){
         CompletableFuture<String> str = CompletableFuture.supplyAsync(() -> "Bulkhead THREADPOOL 系统繁忙，请稍后再试");
        return str;
    }


    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public Result<String> myRatelimit(@PathVariable("id") Integer id){

        return payFeignApi.myRatelimit(id);
    }

    public Result<String> myRatelimitFallback(Integer id, Throwable t){
      return Result.fail("500","你被限流了，禁止访问！"+id);
    }


}
