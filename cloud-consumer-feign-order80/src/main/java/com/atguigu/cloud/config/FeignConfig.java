package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {


    @Bean
    public Retryer myRetryer() {
         return Retryer.NEVER_RETRY;//默认配置不走重试策略

        //初始间隔为100毫秒，重试最大间隔时间为1秒，最大请求次数为3（1+2）
        //return new Retryer.Default(100,1,3);
    }

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


}
