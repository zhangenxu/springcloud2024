package com.atguigu.cloud.juc.CompletableFutureDemos;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //无返回值启动一个线程
//        CompletableFuture completableFuture = CompletableFuture.runAsync(() ->{
//            System.out.println(Thread.currentThread().getName());
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },executorService);

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task over";
        },executorService);

        System.out.println(stringCompletableFuture.get());
        executorService.shutdown();
    }



}
