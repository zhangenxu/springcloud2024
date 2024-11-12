package com.atguigu.cloud.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;


public class AtomicStempDemo {
    public static void main(String[] args) {
        Book javabook = new Book(1,"javabook");
        AtomicStampedReference<Book> atr = new AtomicStampedReference<>(javabook,1);
        System.out.println(atr.getReference()+"\t"+atr.getStamp());
        Book mysqlbook = new Book(2,"mysqlbook");
        atr.compareAndSet(javabook,mysqlbook,atr.getStamp(),atr.getStamp()+1);
        System.out.println(atr.getReference()+"\t"+atr.getStamp());
        boolean b = atr.compareAndSet(mysqlbook,javabook,atr.getStamp(),atr.getStamp()+1);
        System.out.println(b+"\t"+atr.getReference()+"\t"+atr.getStamp());

    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Book{
    int id;
    String name;
}