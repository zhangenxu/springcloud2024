package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController
{
    @Resource
    private PayFeignApi payFeignApi;


    @PostMapping(value = "/feign/pay/add")
    public Result addOrder(PayDto dto){
        return payFeignApi.addPay(dto);
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public Result getPayInfo(@PathVariable("id") Integer id ){
        Result r = null;
        try {
            log.info("调用查询订单信息接口开始================================="+ DateUtil.now());
            r = payFeignApi.getPayInfo(id);
            log.info("调用查询订单信息接口结束================================="+ DateUtil.now());
        }catch (Exception e){
            log.error("调用查询订单信息接口出错================================="+ DateUtil.now());
            return Result.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage() );
        }
        return r;
    }

    /**
     * 操千曲而后晓声，观千剑而后识器
     * 演示负载均衡
     * @return
     */
    @GetMapping(value="feign/pay/mylb")
    public Result mylb(){
        return payFeignApi.mylb();
    }

}
