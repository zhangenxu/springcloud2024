package com.atguigu.cloud.juc.CompletableFutureDemos;

import java.util.concurrent.*;

public class FutureThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        FutureTask fu1 = new FutureTask<String>(() ->{
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });
        executorService.submit(fu1);
        FutureTask fu2 = new FutureTask<String>(() ->{
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2 over";
        });
        executorService.submit(fu2);
        FutureTask fu3= new FutureTask<String>(() ->{
            TimeUnit.MILLISECONDS.sleep(200);
            return "task2 over";
        });
        executorService.submit(fu3);
        System.out.println("任务1返回值："+fu1.get());
        System.out.println("任务1返回值："+fu2.get());
        long end = System.currentTimeMillis();
        String  cost = "系统耗时："+ (end-start)+"毫秒";
        System.out.println(cost);
        executorService.shutdown();
    }

    public void m1(){
        long start = System.currentTimeMillis();



        long end = System.currentTimeMillis();

        String  cost = "系统耗时："+ (end-start);


        System.out.println(Thread.currentThread().getName()+cost);
    }





}
