package com.atguigu.cloud.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"\t"+"被唤醒！！");
        }, "t1");
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName()+"\t"+"发出通知");
        });

    }

    public void awaitSingalDemo() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition c = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"\t"+"come in");
                c.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"被唤醒！！");
        },"t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            lock.lock();
            try {
                c.signal();
                System.out.println(Thread.currentThread().getName()+"\t"+"发出通知");
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }

    public void waitNtifyDemo() throws InterruptedException {
        Object o = new Object();
        new Thread(()->{
            synchronized (o){
                System.out.println(Thread.currentThread().getName()+"\t"+"come in");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"被唤醒！！");
            }
        },"t1").start();
        //暂停1秒
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            synchronized (o){
                o.notify();
                System.out.println(Thread.currentThread().getName()+"\t"+"发出通知");
            }
        },"t2").start();
    }
}
