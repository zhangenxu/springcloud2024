package com.atguigu.cloud.juc.threadlocal;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class ReferenceDemo {
    public static void main(String[] args) throws InterruptedException {

        SoftReference<MyObject> sfr = new SoftReference<>(new MyObject());
        System.out.println("SoftReference:"+sfr);
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(":"+sfr);

    }


    private static void stringReference() throws InterruptedException {
        MyObject myObject = new MyObject();
        System.out.println("gc before:"+myObject);
        myObject = null;
        System.gc();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("gc after:"+myObject);

    }
    static class MyObject{
        @Override
        protected void finalize() throws Throwable {
            System.out.println(" invoke finallized ---");
        }
    }

}
