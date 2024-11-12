package com.atguigu.cloud.juc.objectdemo;

public class ObjectHeadDemo {
    public static void main(String[] args) {
        Object o = new Object();//new 一个对象占多少内存

        synchronized (o){

        }
        System.gc();
    }
}
