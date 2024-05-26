
package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Resource
    private PayMapper mapper;


    @Override
    public int add(Pay pay) {
        return mapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return mapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return mapper.selectAll();
    }

    @Override
    public Pay getByOrderNo(String orderNo) {
        Example e = new Example(Pay.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("orderNo",orderNo);
        List<Pay> payList = mapper.selectByExample(e);
        return payList.get(0);
    }


}

