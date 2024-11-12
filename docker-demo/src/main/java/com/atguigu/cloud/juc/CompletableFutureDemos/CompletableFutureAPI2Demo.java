package com.atguigu.cloud.juc.CompletableFutureDemos;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CompletableFutureAPI2Demo {
    /**
     * thenApply 有异常不继续处理
     * handle() 有异常继续处理
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        },executorService).thenApply(f->{
            log.info("进入第二步，当前结果为："+f);
            return  f+1;
        }).thenApply(f->{
            log.info("进入第三步,当前结果为："+f);
            return  f+1;
        }).whenComplete((v,e)->{
            if (e == null){
                log.info("计算完成，结果为："+ v);
            }
        }).exceptionally((e)->{
            e.printStackTrace();
            log.info("计算出错："+e.getMessage());
            return null;
        });
        log.info(Thread.currentThread().getName()+" 主线程去忙其他任务了----------");
        executorService.shutdown();
    }
}
