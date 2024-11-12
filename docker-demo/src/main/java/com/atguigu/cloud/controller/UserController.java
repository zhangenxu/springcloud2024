package com.atguigu.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.atguigu.cloud.entities.User;
import com.atguigu.cloud.entities.UserDto;
import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Tag(name = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService service;

    @PostMapping("/add")
    @Operation(summary = "新增", description = "新增用户")
    public Result<String> add(@RequestBody UserDto userDto) {
        User user = new User();
        BeanUtil.copyProperties(userDto,user);
        user.setCreateTime(new Date());
        user.setDeleted(0);
        service.addUser(user);
        return Result.success("添加用户成功！");
    }

    @GetMapping("/findByUserId/{userId}")
    @Operation(summary = "查询", description = "根据id查询用户")
    public Result<UserDto> findByUserId(@PathVariable("userId") String userId) {
        UserDto userDto = new UserDto();
        User u = service.findByUserId(userId);
        BeanUtil.copyProperties(u,userDto);
        return Result.success(userDto);
    }



}

