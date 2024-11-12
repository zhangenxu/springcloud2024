package com.atguigu.cloud.juc.CompletableFutureDemos;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 案例：电商比价需求，模拟如下情况
 * 1、同一款产品，同时搜索出来同款产品在各大电商平台售价
 * 2、同款产品，同时搜索出来本产品在同一个电商平台下，各个入驻买家售价是多少
 *
 * 输出：出来结果希望是同款产品在同一个点上平台下，各个入驻买家售价是多少
 *
 * 技术要求：
 * 函数式编程
 * 链式编程
 * stream流式计算
 */
public class CompletableFutureMallDemo
{
    static List<NetMall> list =Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao")
        );
    public static List<String> getPrise(List<NetMall> nList,String productName ){
        return nList.stream().map(netMall ->
                productName+String.format(" in %s price is %.2f",netMall.getNetMallName(),netMall.calcPrice(productName))
                )
                .collect(Collectors.toList());
    }
    public static List<String> getPriseByCompletableFuture(List<NetMall> nList,String productName ){
        return nList.stream().map(netMall ->
                CompletableFuture.supplyAsync(
                        () ->productName+String.format(" in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))).collect(Collectors.toList())
                .stream().map(s -> s.join()).collect(Collectors.toList());
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrise(list,"mysql");
        for(String s : list1){
            System.out.println(s);
        }
        long endTime = System.currentTimeMillis();
        String cost = "----系统耗时：" + (endTime - startTime)+"毫秒";
        System.out.println(cost);

        long startTime1 = System.currentTimeMillis();
        List<String> list2 = getPriseByCompletableFuture(list,"mysql");
        for(String s : list2){
            System.out.println(s);
        }
        long endTime1 = System.currentTimeMillis();
        String cost1 = "----系统耗时：" + (endTime1 - startTime1)+"毫秒";
        System.out.println(cost1);
    }
}
@Data
class NetMall{
    @Getter
    private String netMallName;
    public NetMall(String netMallName){
        this.netMallName= netMallName;
    }
    public BigDecimal calcPrice(String productName){
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble()*2+productName.charAt(0));
    }
}