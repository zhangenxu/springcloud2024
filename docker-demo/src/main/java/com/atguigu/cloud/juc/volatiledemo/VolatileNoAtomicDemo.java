package com.atguigu.cloud.juc.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * volatile非原子性案例
 */
public class VolatileNoAtomicDemo {

    public static void main(String[] args) throws InterruptedException {
        MyNumber number = new MyNumber();
        for (int i = 0; i<10; i++){
            new Thread(()->{
                for (int j =1; j<=1000; j++){
                    number.addPlus();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(number.number);
    }
}

class MyNumber{

    //int number;
    volatile int number;
//    public synchronized void addPlus(){
//        number++;
//    }
    public void addPlus(){
        number++;
    }

}