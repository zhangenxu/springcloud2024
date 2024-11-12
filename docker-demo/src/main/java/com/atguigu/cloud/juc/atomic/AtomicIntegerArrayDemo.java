package com.atguigu.cloud.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray array = new AtomicIntegerArray(5);

        AtomicIntegerArray array1 = new AtomicIntegerArray(new int[5]);
        AtomicIntegerArray array2 = new AtomicIntegerArray(new int[]{1,2,3,4,5});

//        for(int i =0; i<array2.length(); i++){
//            System.out.println(array2.get(i));
//        }
//
//        System.out.println(array.getAndSet(0,11));
        array.getAndIncrement(0);
        System.out.println(array.get(0));
    }
}
