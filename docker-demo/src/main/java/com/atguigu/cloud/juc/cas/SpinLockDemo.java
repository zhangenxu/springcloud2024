package com.atguigu.cloud.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 题目：实现一个自旋锁，复习cas思想
 * 自旋锁好处：循环比较获取，没有类似wait的阻塞
 * 通过CAS操作完成自旋锁，A线程先进来调用mylock方法自己持有锁5秒钟，B随后进来发现
 * 当前有线程持有锁，所以只能通过自行旋等待，直到A释放锁后B随后抢到
 */
public class SpinLockDemo {

    AtomicReference<Thread> at = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"\t come in");
        while (!at.compareAndSet(null,thread)){

            System.out.println("自循环当中，当前线程名称："+thread.getName());
        }
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        at.compareAndSet(thread,null);
        System.out.println(thread.getName()+" : task over,unlock...");
    }
    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo demo = new SpinLockDemo();
        new Thread(()->{
            demo.lock();
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {throw new RuntimeException(e);}
            demo.unLock();
        },"A").start();
        //让A线程优先调用8
        TimeUnit.MILLISECONDS.sleep(2000);
        new Thread(()->{
            demo.lock();
            demo.unLock();
        },"B").start();

    }



}
