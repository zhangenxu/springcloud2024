package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGateWayController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/pay/gateway/get/{id}")
    public Result<PayDto> getGatewayById(@PathVariable("id")Integer id){
        Result<PayDto> dtoResult = payFeignApi.getGatewayById(id);
        return dtoResult;
    }

    @GetMapping(value = "/pay/gateway/info")
    public Result<String> getGatewayInfo(){
        return Result.success("gateway info test "+ IdUtil.simpleUUID());
    }

}
