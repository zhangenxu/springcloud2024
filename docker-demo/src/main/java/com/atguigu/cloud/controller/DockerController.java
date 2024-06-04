package com.atguigu.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class DockerController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/docker/port")
    public String getPort(){

        return "该应用程序的端口为："+serverPort+" 当前时间为:"+ System.currentTimeMillis();
    }


}
