package com.atguigu.cloud.Controller;

import com.atguigu.cloud.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class EmpowerController {


    @GetMapping(value = "/empower")
    public Result<String> empower(){
        System.out.println("测试授权规则");
        return Result.success(System.currentTimeMillis()+"sentinel授权规则！");
    }





}
