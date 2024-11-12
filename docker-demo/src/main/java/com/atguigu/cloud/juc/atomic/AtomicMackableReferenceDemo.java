package com.atguigu.cloud.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMackableReferenceDemo {

    static AtomicMarkableReference mk = new AtomicMarkableReference(100,false);
    public static void main(String[] args) {

        new Thread(()->{
            boolean mark = mk.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t 当前标记状态："+mark+"\t當前值："+mk.getReference());
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}
            mk.compareAndSet(100,1000,mark,!mark);
        },"t1").start();
        new Thread(()->{
            boolean mark = mk.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t 当前标记状态："+mark+"\t當前值："+mk.getReference());
            try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {throw new RuntimeException(e);}
            mk.compareAndSet(100,2000,mark,!mark);
            System.out.println(Thread.currentThread().getName()+"\t 修改后標記狀態："+ mk.isMarked()+"\t當前值："+mk.getReference());

        },"t2").start();

    }
}
