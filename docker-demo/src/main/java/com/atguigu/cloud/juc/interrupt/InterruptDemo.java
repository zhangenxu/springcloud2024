package com.atguigu.cloud.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptDemo {
    static volatile boolean isStop = false;

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args) throws InterruptedException {
        InterruptDemo d  = new InterruptDemo();
        d.m3_Interrupt();
    }

    public void m3_Interrupt() throws InterruptedException {
       Thread t1 = new Thread(()->{
           while (true){
               if(Thread.currentThread().isInterrupted()){
                   System.out.println("线程中断了！！！！！！");
                   break;
               }
               System.out.println("线程没有被中断！程序继续");
           }
       },"t1");
       t1.start();
       TimeUnit.MILLISECONDS.sleep(20);
       new Thread(()->{
           t1.interrupt();
           System.out.println("t2线程告诉t1你需要中断！！！");
       },"t2").start();


    }
    public void m2_atomicBoolean(){
        new Thread(()->{
            while (true){
                if(atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName()+"\t"+"线程停止");
                    break;
                }
                System.out.println("hello come in ");
            }
        },"t1").start();
        try {TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()->{
            System.out.println("进入t2线程-----");
            atomicBoolean.set(true);
        },"t2").start();
    }
    public void m1_volatile(){
        new Thread(()->{
            while (true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName()+"\t"+"线程停止");
                    break;
                }
                System.out.println("hello come in ");
            }
        },"t1").start();
        try {TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()->{
            System.out.println("进入t2线程-----");
            isStop = true;
        },"t2").start();
    }
}
