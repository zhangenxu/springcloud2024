package com.atguigu.cloud.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class AccumulatoCompareDemo {

    public static final int threadNumber = 50;

    public static final int _1w = 10000;

    /**
     * 50个线程，每个线程点赞数100W次，总的累加
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        //统计线程
        CountDownLatch count = new CountDownLatch(threadNumber);
        CountDownLatch count1 = new CountDownLatch(threadNumber);
        CountDownLatch count2 = new CountDownLatch(threadNumber);
        CountDownLatch count3 = new CountDownLatch(threadNumber);
        //统计时间
        Long start;
        Long end;
        ClickNumber clickNumber = new ClickNumber();
        /*****synchronized计算方式******************/
        start = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= _1w*100 ; j++) {
                        clickNumber.add();
                    }
                }finally {
                    count.countDown();
                }
            },String.valueOf(i)).start();
        }
        count.await();
        end = System.currentTimeMillis();
        System.out.println("传统synchronized计算方法，计算结果："+clickNumber.number+"\t总共耗时："+(end - start)+"毫秒");
        /*****synchronized计算方式******************/
        /*****AtomicLong计算方式******************/
        start = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= _1w*100 ; j++) {
                        clickNumber.atomiclongAdd();
                    }
                }finally {
                    count1.countDown();
                }
            },String.valueOf(i)).start();
        }
        count1.await();
        end = System.currentTimeMillis();
        System.out.println("传统AtomicLong计算方法，计算结果："+clickNumber.aLong.get()+"\t总共耗时："+(end - start)+"毫秒");
        /*****AtomicLong计算方式******************/
        /*****longAdder计算方式******************/
        start = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= _1w*100 ; j++) {
                        clickNumber.longAdderAdd();
                    }
                }finally {
                    count2.countDown();
                }
            },String.valueOf(i)).start();
        }
        count2.await();
        end = System.currentTimeMillis();
        System.out.println("传统longAdder计算方法，计算结果："+clickNumber.longAdder.sum()+"\t总共耗时："+(end - start)+"毫秒");
        /*****longAdder计算方式******************/
        /*****LongAccumulator计算方式******************/
        start = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 1; j <= _1w*100 ; j++) {
                        clickNumber.longAccumulatorAdd();
                    }
                }finally {
                    count3.countDown();
                }
            },String.valueOf(i)).start();
        }
        count3.await();
        end = System.currentTimeMillis();
        System.out.println("LongAccumulator计算方法，计算结果："+clickNumber.accumulator.get()+"\t总共耗时："+(end - start)+"毫秒");
        /*****LongAccumulator计算方式******************/



    }
    //操作资源类
    static class ClickNumber{

        /****传统方法***************/
        int number = 0;
        public synchronized void add(){
            number++;
        }
        AtomicLong aLong = new AtomicLong(0);
        public void  atomiclongAdd(){
            aLong.incrementAndGet();
        }
        LongAdder longAdder = new LongAdder();
        public void longAdderAdd(){
            longAdder.increment();
        }
        LongAccumulator accumulator = new LongAccumulator((x,y)->x+y,0);
        public void longAccumulatorAdd(){
            accumulator.accumulate(1);
        }
    }

}
