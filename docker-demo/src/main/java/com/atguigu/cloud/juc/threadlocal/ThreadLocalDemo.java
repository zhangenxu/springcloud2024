package com.atguigu.cloud.juc.threadlocal;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 需求：5个销售卖房子，集团高层只关心卖出房子总量的准确统计数
 *
 * 需求2:5个销售卖完随机数房子，各自独立销售额度，自己业绩按照提成走，分灶吃饭，各自销售自己动手丰衣足食
 */
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        CountDownLatch count = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                try {
                    //创建一个随机数
                    int saile_house = new Random().nextInt(5) + 1;
                    //System.out.println(Thread.currentThread().getName()+"卖出："+saile_house+"套房子");
                    try {
                        for (int j = 0; j < saile_house; j++) {
                            house.addHouseCount();
                            house.coutEverySales();
                        }
                        System.out.println(Thread.currentThread().getName() + "卖出：" + house.init.get() + "套房子");
                    } finally {
                        count.countDown();
                    }
                }finally {
                    house.init.remove();
                }
            },i+"号销售").start();
        }
        count.await();
        System.out.println("所有销售总计卖出："+house.house_count+"套房子！！！");
    }

}

class House{
    public Integer house_count = 0;
    public synchronized void addHouseCount(){
        ++house_count;
    }
    //创建一个线程的局部变量，并且设置一个初始值
//    ThreadLocal init = new ThreadLocal(){
//        @Override
//        protected Object initialValue() {
//            return 0;
//        }
//    };
    ThreadLocal<Integer> init = ThreadLocal.withInitial(()->0);

    /**
     * 结算每个销售卖出多少套
     */
    public void coutEverySales(){
        init.set(1+init.get());
    }

}