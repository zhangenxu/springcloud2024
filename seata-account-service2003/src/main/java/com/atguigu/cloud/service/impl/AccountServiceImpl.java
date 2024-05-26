package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.AccountMapper;
import com.atguigu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper mapper;

    @Override
    public void accountDecrease(Long userId, BigDecimal money) {
        log.info("账户服务扣减账户开始！");
        mapper.decrease(userId,money);
        //int age = 10/0
        //myTimeout();
        log.info("账户服务扣减账户结束！");
    }

    public void myTimeout(){
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
