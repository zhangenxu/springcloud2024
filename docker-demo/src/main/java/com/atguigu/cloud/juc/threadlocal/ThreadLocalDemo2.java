package com.atguigu.cloud.juc.threadlocal;

import org.apache.catalina.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 必须回收自定义的ThreadLocal变量，尤其是在线程池场景下，线程经常会被复用，如果不清理
 * 自定义的ThreadLocal变量，可能会影响到后续的业务逻辑和造成内存泄露等问题。尽量在代理
 * 中使用try-finally块进行回收
 */
public class ThreadLocalDemo2 {

    static class MyData{
        ThreadLocal<Integer> aa = ThreadLocal.withInitial(()->0);
        public void add(){
            aa.set(aa.get()+1);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        MyData myData = new MyData();
        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> {
                    try {
                        Integer before = myData.aa.get();
                        myData.add();
                        Integer after = myData.aa.get();
                        System.out.println(Thread.currentThread().getName() + "\t" + "修改前：" + before + "\t 修改过后：" + after);
                    } finally {
                        myData.aa.remove();
                    }
                });
            }
        }finally {
            executorService.shutdown();
        }
    }
}
