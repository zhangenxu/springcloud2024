package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AcclountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper mapper;

    @Resource
    private StorageFeignApi storageFeignApi;

    @Resource
    private AcclountFeignApi acclountFeignApi;

    @Override
    @GlobalTransactional(name = "zzyy-create-order",rollbackFor = Exception.class)
    public void creatOrder(Order order) {
        String xid = RootContext.getXID();
        log.info("新建订单开始，获得全局事务id:"+xid);
        order.setStatus(0);
        int result = mapper.insertSelective(order);
        Order orderDb = null;
        if(result > 0){
            orderDb = mapper.selectOne(order);
            log.info("新建订单成功，开始调用减库存服务---------");
            storageFeignApi.storageDecrease(orderDb.getProductId(),orderDb.getCount());
            log.info("扣减库存成功，开始调用扣减账户服务---------");
            acclountFeignApi.accountDecrease(orderDb.getUserId(),orderDb.getMoney());
            log.info("扣减账户成功，开始调回写订单状态---------");
            orderDb.setStatus(1);
            Example example = new Example(Order.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",orderDb.getUserId());
            criteria.andEqualTo("status",0);
            int i = mapper.updateByExampleSelective(order, example);
            log.info("修改订单状态完成,订单状态："+ i);
            log.info("orderDb："+ orderDb);
        }
        log.info("新建订单结束，xid:"+xid);
    }
}
