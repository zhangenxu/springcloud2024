package com.atguigu.cloud.mygateway;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义配置会员等级userType,按照钻/金/银/和yml配置会员等级，以适配是否可以访问
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {


    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    //钻石用户允许访问
    //http://localhost:9527/pay/gateway/get/1?userType=diamond
    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
               String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
               if(userType == null){
                   return false;
               }
               if(userType.equalsIgnoreCase(config.getUserType())){
                   return true;
               }
               return false;
            }
        };
    }

    @Validated
    public static class Config{
        @Setter
        @Getter
        @NotNull
        private String userType;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }
}
