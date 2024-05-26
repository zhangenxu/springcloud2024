package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count   扣减数量
     * @return
     */
    @PostMapping(value = "/storage/decrease")
    Result<String> storageDecrease(@RequestParam("productId") Long productId
            ,@RequestParam("count") Integer count);




}
