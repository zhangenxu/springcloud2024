package com.atguigu.cloud.juc.interrupt;


import java.util.concurrent.TimeUnit;


public class InterruptDemo4 {
    public static void main(String[] args) throws InterruptedException {

        //测试当前线程是否被中断（检查中断标志）返回一个boolean并清除中断状态
        //第二次调用时，中断状态已被清除，返回一个false
        System.out.println(Thread.currentThread().interrupted());
        System.out.println(Thread.currentThread().interrupted());

        Thread.currentThread().interrupt();

        System.out.println(Thread.currentThread().interrupted());
        System.out.println(Thread.currentThread().interrupted());


    }



}
