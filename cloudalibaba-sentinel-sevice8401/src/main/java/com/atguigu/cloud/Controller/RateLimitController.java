package com.atguigu.cloud.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.cloud.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping(value = "/rateLimit/byUrl")
    public Result<String> rateLimitByUrl(){
        return Result.success("按照rest地址限流！！！！");
    }

    @GetMapping(value = "/rateLimit/byResource")
    @SentinelResource(value = "byResourcelSentinelResource",blockHandler = "handlerblockHandler")
    public Result<String> rateLimitByResourcel(){
        return Result.success("按照资源名称限流！！！！");
    }

    public Result<String> handlerblockHandler(BlockException blockException){
        return Result.fail("500","按照资源名称限流自定义返回");
    }


    @GetMapping(value = "/rateLimit/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource",blockHandler = "doActionblockHandler",fallback = "doActionFallback")
    public Result<String> doActionCentinelResource(@PathVariable("p1") Integer p1){
        if(p1 == 0){
            throw new RuntimeException("pi=0异常");
        }
        return Result.success("doAction");
    }

    public Result<String> doActionblockHandler(@PathVariable("p1") Integer p1,BlockException blockException){
        return Result.fail("500","配置自定义限流鬼规则:"+p1);
    }
    public Result<String> doActionFallback(@PathVariable("p1") Integer p1,Throwable e){
        return Result.fail("500","程序逻辑异常:"+e.getMessage());
    }


    /**
     * 热点参数限流
     * @return
     */

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKeySentinelResource",blockHandler = "testHotKeyblockHandler")
    public Result<String> testHotKey(@RequestParam(value = "p1",required = false) String p1,
                                     @RequestParam(value = "p2",required = false) String p2){
        return Result.success("热点参数限流！！！！");
    }

    public Result<String> testHotKeyblockHandler(String p1,String p2,BlockException blockException){
        return Result.fail("500","热点参数限流自定义返回");
    }



}
