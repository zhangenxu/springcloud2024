package com.atguigu.cloud.juc.interrupt;


import java.util.concurrent.TimeUnit;

/**
 * 当一个线程调用interrupt时
 * 1、如果线程处于正常活动状态，那么会将线程的中断标志设置为true,仅此而已。被设置中断标志的线程将会正常运行，不受影响。
 * 所以interrupt()并不能真正中断线程，需要被调用的线程自己进行配合才行。
 * 2、如果线程处于被阻塞状态（例如sleep、wait、join等状态），在别的线程调用当前线程的interrupt方法，那么线程将立即退出被阻塞状态
 * 并抛出一个interruptException异常
 */
public class InterruptDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
           for(int i =1; i<=300; i++){
               System.out.println("线程t1正在循环，当前循环到："+i);
           }
            System.out.println("当前线程是否被中断02：" + (Thread.currentThread().isInterrupted()== true?"中断了!":"未中断!") );
        },"t1");
        t1.start();
        System.out.println("t1线程默认中断标识："+t1.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("正在告诉t1线程要中断了");
        t1.interrupt();
        System.out.println("两秒后线程t1状态01："+t1.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(5000);
        System.out.println("几秒后线程t1状态03："+t1.isInterrupted());
    }



}
