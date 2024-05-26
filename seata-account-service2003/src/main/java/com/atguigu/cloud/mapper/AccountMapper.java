package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Account;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;

public interface AccountMapper extends Mapper<Account> {

    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);

}