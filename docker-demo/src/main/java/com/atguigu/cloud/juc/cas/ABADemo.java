package com.atguigu.cloud.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicInteger at = new AtomicInteger(100);

    static AtomicStampedReference ats = new AtomicStampedReference(100,1);

    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            int stemp = ats.getStamp();
            System.out.println("版本号："+stemp);
            ats.compareAndSet(100,200,stemp,stemp+1);
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}
            ats.compareAndSet(200,100, ats.getStamp(), ats.getStamp()+1);
        },"t3").start();
        try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {throw new RuntimeException(e);}
        new Thread(()->{
            int stemp = ats.getStamp();
            System.out.println("版本号："+stemp);
            boolean b = ats.compareAndSet(100,200,stemp,stemp+1);
            StringBuffer sb = new StringBuffer();
            sb.append("本次修改：").append((b?"成功":"失败")).append("\t").append("版本号：").append(ats.getStamp())
                            .append("内容：").append(ats.getReference());
            System.out.println(sb);
        },"t4").start();


    }

    public void aba() throws InterruptedException {
        new Thread(()->{
            at.compareAndSet(100,200);
            try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {throw new RuntimeException(e);}
            at.compareAndSet(200,100);
        },"t1").start();
        TimeUnit.MILLISECONDS.sleep(500);
        new Thread(()->{
            at.compareAndSet(100,2022);
            System.out.println(at.get());
        },"t2").start();
    }
}
