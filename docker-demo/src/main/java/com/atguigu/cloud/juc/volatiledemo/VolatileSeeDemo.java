package com.atguigu.cloud.juc.volatiledemo;

import java.util.concurrent.TimeUnit;

public class VolatileSeeDemo {

    static volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName()+"\t end flag is false:"+false);
        },"t1").start();

        TimeUnit.SECONDS.sleep(2);
        flag= false;
        System.out.println("主线程完成了flag的修改flag="+flag);
    }
}
