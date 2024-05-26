package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    private AccountService storageService;

    @RequestMapping("/account/decrease")
    Result<String> accountDecrease(@RequestParam("userId") Long userId,@RequestParam("money") BigDecimal money){
        storageService.accountDecrease(userId,money);
        return Result.success("扣减账户成功！");
    };


}
