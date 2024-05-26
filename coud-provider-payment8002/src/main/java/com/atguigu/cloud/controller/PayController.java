package com.atguigu.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name="支付微服务模块",description = "支付CURD")
public class PayController {



    @Resource
    private PayService service;

    @PostMapping("pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法，json串做参数")
    public Result<String> add(@RequestBody PayDto payDto){
        Pay pay = new Pay();
        BeanUtil.copyProperties(payDto,pay);
        Integer i = service.add(pay);
        if(i>0){
           return Result.success("成功插入"+i+"条记录") ;
        }else{
            return Result.fail("500","插入失败");
        }
    }
    @Operation(summary = "删除",description = "删除支付流水")
    @DeleteMapping("/pay/delete/{id}")
    public Result<String> delete(@PathVariable("id") Integer id){
        int i =  service.delete(id);
        return Result.success("成功删除"+i+"条记录");
    }


    @PostMapping("pay/update")
    @Operation(summary = "修改",description = "修改支付流水")
    public Result<String> update(@RequestBody PayDto payDto){
        Pay pay = new Pay();
        BeanUtil.copyProperties(payDto,pay);
        Integer i = service.update(pay);
        if(i>0){
            return Result.success("修改成功"+i+"条记录") ;
        }else{
            return Result.fail("500","修改失败");
        }
    }

    @GetMapping("/pay/getById/{id}")
    @Operation(summary = "查询",description = "查询支付流水")
    public Result<PayDto> getById(@PathVariable("id") Integer id){
        if(id <1){
           throw new RuntimeException("id不能小于1");
        }
        Pay pay =  service.getById(id);
        PayDto dto = new PayDto();
        BeanUtil.copyProperties(pay,dto);
        return Result.success(dto);
    }



    @Value("${server.port}")
    private String port;


    @GetMapping("/pay/getInfoByConsul")
    @Operation(summary = "获取",description = "获取consul配置信息")
    public Result<String> getInfoByConsul(@Value("${atguigu.info}") String consulInfo){
        StringBuilder sb = new StringBuilder();
        sb.append("从consul上获取到的配置信息为：").append(consulInfo).append(" ").append("服务器端口为：")
                .append(port);
        return Result.success(sb.toString());
    }


}
