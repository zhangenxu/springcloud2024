package com.atguigu.cloud.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
public class AtomicIntegerDemo {


    public static int size = 50;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(size);
        MyNumber number = new MyNumber();
        for(int i =1;i<=size; i++){
            new Thread(()->{
                try {
                    for (int j =1; j<=1000; j++ ){
                        number.add();
                    }
                }finally {
                    count.countDown();
                }
            },String.valueOf(i)).start();
        }
        count.await();
        //TimeUnit.SECONDS.sleep(6);
       StringBuilder sb = new StringBuilder();
        sb.append("当前线程：").append(Thread.currentThread().getName()).append("\t")
                .append("计算结果：").append(number.atomicInteger.get());
        System.out.println(sb);

    }




}

class MyNumber{
    AtomicInteger atomicInteger = new AtomicInteger();
    public void add(){
        atomicInteger.getAndIncrement();
    }
}
