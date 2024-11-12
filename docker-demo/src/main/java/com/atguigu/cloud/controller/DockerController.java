package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "docker模块")
@RestController
@RequestMapping("/docker")
public class DockerController {

    @Value("${server.port}")
    private String serverPort;

    @Operation(summary = "获取端口",description = "获取系统端口")
    @GetMapping(value = "/port")
    public Result getPort(){

        return Result.success("该应用程序的端口为："+serverPort+" 当前时间为:"+ System.currentTimeMillis());
    }


}
