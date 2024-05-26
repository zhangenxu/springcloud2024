package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider" ,fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignSentinelApi {

    @GetMapping(value = "pay/nacos/get/{orderNo}")
    Result<PayDto> getPayByOrderNO(@PathVariable("orderNo") String orderNo);




}
