package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.StorageMapper;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageMapper mapper;

    @Override
    public void storageDecrease(Long productId, Integer count) {
        log.info("库存服务扣减库存开始！");
        mapper.decrease(productId,count);
        log.info("库存服务扣减库存结束！");
    }

}
