package com.atguigu.cloud.juc.CompletableFutureDemos;

import java.util.concurrent.*;

public class CompletableFutureUserDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName()+"----come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("1秒后出结果"+ result);
                return result;
            },executorService).whenComplete((v,e)->{
                if(e == null){
                    System.out.println("计算完成:"+v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println(e.getCause()+"\t"+e.getMessage());
                return null;
            });
        }catch (Exception e){

        }finally {
            executorService.shutdown();
        }


        System.out.println(Thread.currentThread().getName()+"--主线程忙其他任务去了");
        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭(当成守护线程)：暂停3秒钟线程
       //TimeUnit.SECONDS.sleep(3);

    }

    public static void future1()throws ExecutionException, InterruptedException{
        CompletableFuture<Integer> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"----come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1秒后出结果"+ result);
            return result;
        });
        System.out.println(stringCompletableFuture.get());
        System.out.println(Thread.currentThread().getName()+"--主线程忙其他任务去了");
    }


}
