package com.atguigu.cloud.juc.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntergerFiledUpdaterDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(10);
        BankAccount bankAccount = new BankAccount();
        for(int i = 1; i<=10; i++){
            new Thread(()->{
                try {
                    for (int j= 1;j<=100;j++){
                        //bankAccount.addMoney();
                        bankAccount.transMoney(bankAccount);
                    }
                }finally {
                    count.countDown();
                }
            },String.valueOf(i)).start();
        }
        count.await();
       // TimeUnit.SECONDS.sleep(5);
        System.out.println(bankAccount.money);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BankAccount{

    String bankName;

    public volatile int money;
//将对象锁更改
//    public synchronized void addMoney(){
//        money = NumberUtil.add(money,NumberUtil.toBigDecimal("1"));
//    }

    public void addMoney(){
        money ++;
    }

    AtomicIntegerFieldUpdater<BankAccount> updater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money");

    public void transMoney(BankAccount account){
       updater.getAndIncrement(account);
    }

}
