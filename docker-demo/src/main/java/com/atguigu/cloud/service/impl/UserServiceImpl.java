package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.User;
import com.atguigu.cloud.mapper.UserMapper;
import com.atguigu.cloud.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;
    @Resource
    private RedisTemplate redisTemplate;

    String CACHE_KEY_USER = "usrid:";


    @Override
    public void addUser(User user) {
        int i = mapper.insertSelective(user);
        if (i>0){
            user = mapper.selectByPrimaryKey(user.getId());
            String key  = CACHE_KEY_USER + user.getId();
            redisTemplate.opsForValue().set(key,user);
        }
    }

    /**
     * QPS小于1000可以这么写
     * @param userId
     * @return
     */
    @Override
    public User findByUserId(String userId) {
        String key = CACHE_KEY_USER+userId;
        User u = (User) redisTemplate.opsForValue().get(key);
        if(Objects.isNull(u)){
            u = mapper.selectByPrimaryKey(userId);
            if(Objects.isNull(u)){
                //具体细化，防止多次穿透，我们业务规定，记录下这个null值的key,列入黑名单或者记录异常
                return u;
            }else{
                //mysql有，需要将数据回写redis，保证下一次缓存命中
                redisTemplate.opsForValue().set(key,u);
            }
        }
        return u;
    }

    /**
     * redis双检加锁
     * @param userId
     * @return
     */
    public User findByUserId2(String userId) {
        String key = CACHE_KEY_USER+userId;
        User u = (User) redisTemplate.opsForValue().get(key);
        if(Objects.isNull(u)){
            //大厂用，对于高QPS的优化，进来就先加锁，保证一个请求操作，让外面的redis等待一下，避免击穿mysql
            synchronized (this) {
                u = (User) redisTemplate.opsForValue().get(key);
                //二次检查redis还是null，可以去查myql了（mysql默认有数据）
                if (Objects.isNull(u)) {
                    u = mapper.selectByPrimaryKey(userId);
                    if(u == null ){
                        return u;
                    }else{
                        redisTemplate.opsForValue().setIfAbsent(key, u,7L, TimeUnit.DAYS);
                    }
                }
            }
        }
        return u;
    }
}
