//package com.atguigu.cloud.juc.objectdemo;
//
//import org.openjdk.jol.info.ClassLayout;
//import org.openjdk.jol.vm.VM;
//
//public class JOLDemo {
//    public static void main(String[] args) {
//        //vm的细节详细情况
//        System.out.println(VM.current().details());
//        //所有的对象分配的字节都是8的整数倍
//        System.out.println(VM.current().objectAlignment());
//
//        Object o = new Object();
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//
//    }
//}
//
//class Customer{
//    int id;
//    boolean flag = false;
//}
