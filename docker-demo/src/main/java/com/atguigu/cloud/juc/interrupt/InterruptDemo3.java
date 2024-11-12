package com.atguigu.cloud.juc.interrupt;


import java.util.concurrent.TimeUnit;


public class InterruptDemo3 {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("线程中断了！");
                    break;
                }
                try {Thread.sleep(200); } catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();

                }
                System.out.println("线程继续。。。。。。。。。。。。");
            }
        },"t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            t1.interrupt();
        }).start();

    }



}
