package com.atguigu.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.cloud.resp.Result;
import org.springframework.stereotype.Service;

@Service
public class FlowLimitService {

    @SentinelResource(value = "common")
    public Result<String> common(){
        return Result.success("通用链路调用1111");
    }





}
