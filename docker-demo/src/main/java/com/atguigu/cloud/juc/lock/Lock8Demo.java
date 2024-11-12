package com.atguigu.cloud.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 题目：谈谈你对多线程的理解，8种案例说明
 * 1、标准访问有ab两个线程，请问现在打印邮件还是先打印短信
 * 2、sendEmail方法中加入暂停3秒，请问现在打印邮件还是先打印短信
 * （对象锁，锁当前对象this）
 * 1-2总结：一个对象里有多个 synchronized 方法，某一时刻内，只要一个线程去调用其中的一个 synchronized 方法了
 * 其他的线程都只能等待，换句话说，某一时刻内，只能有唯一的一个县城去访问这些 synchronized 方法
 * 锁的当前对象是this，被说锁定后，其他的线程都不能进入到当前对象的其他的 synchronized 方法
 *
 * 3、添加一个普通的hello方法，请问现在打印邮件还是先打印短信
 * 4、有两部手机，请问现在打印邮件还是先打印短信
 * 3-4总结：
 *加普通方法后和和同步锁无关，
 *换成两个对象后，不是同一把锁
 *
 * 5、有两个静态同步方法，有一部手机，请问现在打印邮件还是先打印短信
 * 6、有两个静态同步方法，有两部手机，请问现在打印邮件还是先打印短信
 *5-6总结：都换成静态同步方法，锁的是类
 * 对于普通同步方法，锁的时候当前实例对象，通常指this，具体的一部手机，所有的普通同步方法都用同一把锁-》实例对象本身
 * 对于静态同步方法，锁的时候当前clss对象，如phone.class唯一的一个模板
 * 对于同步方法块，锁的是synchronized 括号内的对象
 *
 * 7、有一个静态同步方法，有一个普通同步方法，有一部手机，请问现在打印邮件还是先打印短信
 * 8、有一个静态同步方法，有一个普通同步方法，有两部手机，请问现在打印邮件还是先打印短信
 * 7-8总结：
 *当一个线程视图访问同步代码块时，必须获得锁，正常退出或者抛出异常时必须释放锁
 * 所有普通同步方法都是同一把锁，实例对象本身，就是new出来的具体实例对象本身，本类this
 * 也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放后才能获取锁
 *
 * 所有的静态同步方法用的也是同一把锁-类对象本身，就是我们说过的唯一模板class
 * 具体实例对象this和唯一模板class,这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞争条件的
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁
 *
 *
 */
public class Lock8Demo {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            phone.sendEmail();
        },"a").start();
        //暂停200毫秒
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(()->{
            phone.sendSMS();
            //phone.hello();
            //phone2.sendSMS();
        },"b").start();



    }
}

/**
 * 结论：1、只要加了synchronized方法，就会锁定整个资源类
 */

@Slf4j
class Phone{
    public synchronized void sendEmail(){
        //try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
        log.info("发送一条邮件！！！");
    }
    public synchronized void sendSMS(){
        log.info("发送一条短信！！！");
    }

    public void hello(){
        log.info("hello word！！！");
    }
}