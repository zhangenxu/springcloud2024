package com.atguigu.cloud.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);

        System.out.println(integer.compareAndSet(5,200)+"\t"+integer.get());
        System.out.println(integer.compareAndSet(5,200)+"\t"+integer.get());


    }
}
