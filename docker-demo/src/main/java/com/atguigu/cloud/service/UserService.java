package com.atguigu.cloud.service;


import com.atguigu.cloud.entities.User;

public interface UserService {

    void addUser(User user);

    User findByUserId(String userId);
}
