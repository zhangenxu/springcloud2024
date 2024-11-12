package com.atguigu.cloud.juc.CompletableFutureDemos;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 结果集合并
 */
@Slf4j
public class CompletableFutureCombineDemo {

    public static void main(String[] args) {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            log.info("线程" + Thread.currentThread().getName() + "开始运行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            log.info("线程" + Thread.currentThread().getName() + "开始运行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        });
        CompletableFuture<Integer> result = f1.thenCombine(f2, (x, y) -> {
            return x + y;
        });
        System.out.println(result.join());
    }
}
