package com.atguigu.cloud.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 需求：
 * 多线程并发调用一个类的初始化方法，如果未被初始化过，将执行初始化工作
 * 要求只能被初始化一次，只有一个线程操作成功
 *
 */
public class AtomicReferenceFieldUpdaterDemo {

    public static void main(String[] args) {
        MyVar myVar = new MyVar();
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                try {myVar.init(myVar);} catch (InterruptedException e) {throw new RuntimeException(e);}
            },String.valueOf(i)).start();
        }
    }

}

class MyVar{
    public volatile Boolean flag = false;
    AtomicReferenceFieldUpdater<MyVar,Boolean> updater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class,Boolean.class,"flag");
    public void init(MyVar myVar) throws InterruptedException {
        System.out.println("线程："+Thread.currentThread().getName()+"\t进入初始化方法");
        if(updater.compareAndSet(myVar,Boolean.FALSE,Boolean.TRUE)){
            TimeUnit.SECONDS.sleep(3);//做业务需要3秒
            System.out.println("线程："+Thread.currentThread().getName()+"\t抢到CAS锁，初始化设置数据完成！");
        }else{
            System.out.println("线程："+Thread.currentThread().getName()+"\t未抢到CAS锁，未进行初始化");
        }
    }

}