package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.resp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {


//    @GetMapping(value = "/pay/circuit/{id}")
//    public Result<String> myCircuit(@PathVariable("id") Integer id){
//        if(id <1){
//            throw new RuntimeException("id不能小于1");
//        }
//        if(id == 9999){
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            }catch (Exception e){
//               e.printStackTrace();
//            }
//        }
//
//        return Result.success("Hello ,circuit! imputId"+id +"\t" + IdUtil.simpleUUID());
//    }

    /**
     *resilience4j bulkhead例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public Result<String> myBulkhead(@PathVariable("id") Integer id){
        if(id <1){
            throw new RuntimeException("id不能小于1");
        }
        if(id == 9999){
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return Result.success("Hello ,bulkhead! imputId"+id +"\t" + IdUtil.simpleUUID());
    }


    /**
     * Resilience4j ratelimit 例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public Result<String> myRatelimit(@PathVariable("id")Integer id){
        return Result.success("Hello ,myRatelimit欢迎到来 inputId: " + id +"\t" + IdUtil.simpleUUID());
    }



}
