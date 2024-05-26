package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //在控制器类加入@RefreshScope 注解使当前类下的配置支持Nacos的动态刷新功能
public class NacosConfigClientController {

    @Value("${config.info}")
    private String configinfo;

    @GetMapping(value = "/config/info")
    public Result<String> getConfigInfo(){
        return Result.success(configinfo);
    }




}
