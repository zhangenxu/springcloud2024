package com.atguigu.cloud.juc.CompletableFutureDemos;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask futureTask = new FutureTask(() ->{
            System.out.println(Thread.currentThread().getName()+"\t"+"come in");
            TimeUnit.SECONDS.sleep(5);
            return "task1 over";
        });
        Thread thread = new Thread(futureTask,"t1");
        thread.start();
        System.out.println(Thread.currentThread().getName()+"\t" + "主线程去执行其他任务了");
        //System.out.println(futureTask.get());//只要调用get方法就会等待task任务执行完，容易造成阻塞
        //System.out.println(futureTask.get(3,TimeUnit.SECONDS));//过时不候，抛出异常
        while (true){
            if(futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中，越催越慢，再催熄火！");
            }
        }
    }
}
