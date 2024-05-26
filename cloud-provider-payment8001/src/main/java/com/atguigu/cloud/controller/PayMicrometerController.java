package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.resp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayMicrometerController {


    /**
     * micrometer(sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public Result<String> myMicrometer(@PathVariable("id") Integer id){

        return Result.success("hello,欢迎来到myMicrometer"+id+"\t"+"服务返回："+ IdUtil.simpleUUID());
    }



}
