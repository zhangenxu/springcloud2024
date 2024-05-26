package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi{


    /**
     * 统计降级方法
     * @param orderNo
     * @return
     */
    @Override
    public Result<PayDto> getPayByOrderNO(String orderNo) {
        return Result.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机不可用，服务降级-_-!");
    }
}
