package com.atguigu.cloud.service;

import com.atguigu.cloud.resp.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface StorageService {

    void storageDecrease(Long productId, Integer count);

}
