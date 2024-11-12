package com.atguigu.cloud.juc.lock;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock默认非公平
 * 传一个参数公平
 * 为什么默认非公平：
 * 1、恢复挂挂起的线程到真正的获取还是有时间差的，非公平锁能更充分的利用CPU
 * 的时间片，尽量减少CPU空闲的状态时间。
 * 2、使用多线程很重要的考量点是线程切换的开销，当采用非公平锁时，当1个线程请求获取同步状态，
 * 然后释放同步状态，所以刚释放的线程在此刻获取同步状态的概率就变得非常大，所以就减少了线程
 * 的开销
 *
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        new Thread(()->{for (int i=0;i<55;i++) t.sale();},"a").start();
        new Thread(()->{for (int i=0;i<55;i++) t.sale();},"b").start();
        new Thread(()->{for (int i=0;i<55;i++) t.sale();},"c").start();
    }
}
@Slf4j
class Ticket{//模拟资源类三个售票员卖50张票
    private int number = 50;
    ReentrantLock lock = new ReentrantLock(true);

    public void sale(){
        lock.lock();
        try {
            if (number>0){
               StringBuffer sb = new StringBuffer();
               sb.append("当前线程：").append(Thread.currentThread().getName()).append("卖出第:").append(number--).append("\t")
                               .append("还剩下：").append(number).append("张票");
                log.info(sb.toString());
            }
        }finally {
            lock.unlock();
        }
    }
}

