package com.atguigu.cloud.service;

import java.math.BigDecimal;

public interface AccountService {

    void accountDecrease(Long userId, BigDecimal money);

}
