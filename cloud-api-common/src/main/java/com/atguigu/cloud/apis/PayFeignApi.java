package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    @PostMapping(value = "pay/add")
    public Result addPay(@RequestBody PayDto payDto);

    @GetMapping(value = "pay/getById/{id}")
    public Result getPayInfo(@PathVariable("id") Integer id);

    @GetMapping(value = "pay/getInfoByConsul")
    public Result mylb();

    @GetMapping(value = "/pay/circuit/{id}")
    public Result<String> myCircuit(@PathVariable("id") Integer id);


    /**
     * 舱壁
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public Result<String> myBulkhead(@PathVariable("id") Integer id);

    /**
     * ratelimit
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public Result<String> myRatelimit(@PathVariable("id")Integer id);

    /**
     * micrometer(sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public Result<String> myMicrometer(@PathVariable("id") Integer id);


    @GetMapping(value = "/pay/gateway/get/{id}")
    public Result<PayDto> getGatewayById(@PathVariable("id")Integer id);

    @GetMapping(value = "/pay/gateway/info")
    public Result<String> getGatewayInfo();

}
