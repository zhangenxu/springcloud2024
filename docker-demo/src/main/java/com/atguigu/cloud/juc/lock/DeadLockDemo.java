package com.atguigu.cloud.juc.lock;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        new Thread(()->{
            synchronized (o1){
                StringBuffer sb = new StringBuffer();
                sb.append("线程：").append(Thread.currentThread().getName()).append("\t").append("持有A锁，希望继续持有B锁");
                System.out.println(sb.toString());
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
                synchronized (o2){
                    System.out.println("线程持有了B锁！！！");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (o2){
                StringBuffer sb = new StringBuffer();
                sb.append("线程：").append(Thread.currentThread().getName()).append("\t").append("持有B锁，希望继续持有A锁");
                System.out.println(sb.toString());
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
                synchronized (o1){
                    System.out.println("线程持有了A锁！！！");
                }
            }
        },"B").start();
    }





}
