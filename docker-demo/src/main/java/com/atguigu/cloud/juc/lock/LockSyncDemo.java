package com.atguigu.cloud.juc.lock;

public class LockSyncDemo {

    Object obj = new Object();


    public void m1(){

        synchronized (obj){
            System.out.println("heihei");
        }
    }
    public static void main(String[] args) {

    }
}
