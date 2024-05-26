package com.atguigu.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 调用地址：http://localhost:8001/swagger-ui/index.html
 */
@Configuration
public class swagger3Config {


    @Bean
    public GroupedOpenApi PayApi(){
        return GroupedOpenApi.builder().group("支付微服务模块").pathsToMatch("/pay/**").build();
    }

    @Bean
    public GroupedOpenApi OtherApi(){
        return GroupedOpenApi.builder().group("其他微服务模块").pathsToMatch("/other/**","/others").build();
    }

    public OpenAPI docsOpenApi(){

        return new OpenAPI()
                .info(new Info().title("cloud2024")
                        .description("通用设计reset")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("www.atguigu.com")
                        .url("https://yiyan.baidu.com"));
    }

}
