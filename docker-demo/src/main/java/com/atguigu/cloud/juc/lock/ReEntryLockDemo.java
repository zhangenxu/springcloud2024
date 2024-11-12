package com.atguigu.cloud.juc.lock;

/**
 * 重入锁示例
 */
public class ReEntryLockDemo {

    public static void main(String[] args) {


    }

    public void reEntryM1(){
        Object o = new Object();
        Thread t  = new Thread( ()->{
            synchronized (o){
                System.out.println(Thread.currentThread().getName()+"外层调用");
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+"中层调用");
                    synchronized (o){
                        System.out.println(Thread.currentThread().getName()+"内层调用");
                    }
                }
            }
        });
        t.start();

    }
}
