package com.atguigu.cloud.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> u = new AtomicReference<>();
        User z3  = new User(10,"zhangsan");
        User li4 = new User(20,"lisi");
        u.set(z3);
        System.out.println(u.compareAndSet(z3,li4)+"\t"+u.get().toString());
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User{

    int age;

    String name;

}