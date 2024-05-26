package com.atguigu.cloud.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PayAlibabaController {

    @Resource
    PayService payService;

    @Value("${server.port}")
    public String serverPort;

    @GetMapping(value = "pay/nacos/{id}")
    public Result<String> getPayNacos(@PathVariable("id") Integer id){
        return Result.success("nacos resister :" + serverPort);
    }

    //openfeign+sentinel进行服务降级和流量监控的整合处理case
    @GetMapping(value = "pay/nacos/get/{orderNo}")
    @SentinelResource(value ="getPayByOrderNO",blockHandler = "orderNoBlockHandler")
    public Result<PayDto> getPayByOrderNO(@PathVariable("orderNo") String orderNo){
        PayDto dto = new PayDto();
        Pay p =payService.getByOrderNo(orderNo);
        BeanUtil.copyProperties(p,dto);
        return Result.success(dto);

    }

    public Result orderNoBlockHandler(String orderNo, BlockException e){
        return Result.fail("500","服务不可用");
    }
}
