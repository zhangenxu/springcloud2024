package com.atguigu.cloud.juc.CompletableFutureDemos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> {
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return "abc";
        });

//        System.out.println(f.get(2L,TimeUnit.SECONDS));
//        System.out.println(f.join());//作用和get一样，不抛出异常
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {throw new RuntimeException(e);}
       // System.out.println(f.getNow("马上获取结果，没有则抛出当前这段话！"));
        System.out.println(f.complete("是否打断get方法，有打断则返回true！")+"\t"+f.join());

    }
}
