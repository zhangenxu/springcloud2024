package com.atguigu.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
public class PayGateWayController {
    @Resource
    PayService payService;


    @GetMapping(value = "/pay/gateway/get/{id}")
    public Result<PayDto> getGatewayById(@PathVariable("id")Integer id){
        Pay p = payService.getById(id);
        PayDto dto = new PayDto();
        BeanUtil.copyProperties(p,dto);
        return Result.success(dto);
    }

    @GetMapping(value = "/pay/gateway/info")
    public Result<String> getGatewayInfo(){
        return Result.success("gateway info test "+ IdUtil.simpleUUID());
    }

    @GetMapping(value = "/pay/gateway/filter")
    public Result<String> gatewayFilter(HttpServletRequest request){
       String result = "";
       Enumeration<String> headers = request.getHeaderNames();
       while (headers.hasMoreElements()){
           String headName = headers.nextElement();
           String headValue = request.getHeader(headName);
           System.out.println("请求头名："+ headName + "\t\t\t"+"请求值："+headValue);
           if(headName.equalsIgnoreCase("X-Request-atguigu1")||
           headName.equalsIgnoreCase("X-Request-atguigu2")){
               result = result+headName+"\t"+headValue+"  ";
           }
       }

       System.out.println("======================");
       String customerId = request.getParameter("customerId");
       System.out.println("customerId="+customerId);
        System.out.println("======================");
        String customerName = request.getParameter("customerName");
        System.out.println("customerName="+customerName);




       return Result.success("GatewayFileter 过滤器test:"+result+"\t"+ DateUtil.now());
    }




}
