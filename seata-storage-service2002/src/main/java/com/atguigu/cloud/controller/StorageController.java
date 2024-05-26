package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count   扣减数量
     * @return
     */
    @RequestMapping("/storage/decrease")
    Result<String> storageDecrease(@RequestParam("productId") Long productId
            ,@RequestParam("count") Integer count) {
        storageService.storageDecrease(productId,count);
        return Result.success("扣减库存成功！！");
    }


}
