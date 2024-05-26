package com.atguigu.cloud.Controller;

import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public Result<String> testA(){
        return Result.success("---testA");
    }

    @GetMapping(value = "/testB")
    public Result<String> testB(){
        return Result.success("---testB");
    }

    /**
     * 流控-链路演示
     * C和D两个请求都访问FlowLimitService.common();方法，阈值达到后对c限流对D不管
     */
    @Resource
    private FlowLimitService service;

    @GetMapping(value = "/testC")
    public Result<String> testC(){
        return service.common();
    }

    @GetMapping(value = "/testD")
    public Result<String> testD(){
        return service.common();
    }

    @GetMapping(value = "/testE")
    public Result<String> testE(){
        System.out.println("进度E");
        return Result.success(System.currentTimeMillis()+"排队等待测试！");
    }

}
