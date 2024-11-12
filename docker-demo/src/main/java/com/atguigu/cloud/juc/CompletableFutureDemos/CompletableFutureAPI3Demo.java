package com.atguigu.cloud.juc.CompletableFutureDemos;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CompletableFutureAPI3Demo {
    /**
     *thenAccept 消费处理无返回结果
     * @param args
     */
    public static void main(String[] args) {

        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }).thenApply(f->{
            log.info("进入第二步，当前结果为："+f);
            return  f+1;
        }).thenApply(f->{
            log.info("进入第三步,当前结果为："+f);
            return  f+1;
        }).thenAccept(r ->{

        }).thenAccept(System.out::println);

    }
}
