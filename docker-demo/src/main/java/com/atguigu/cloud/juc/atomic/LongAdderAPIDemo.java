package com.atguigu.cloud.juc.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

public class LongAdderAPIDemo {
    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        adder.increment();
        adder.increment();
        System.out.println(adder.sum());

        //LongAccumulator accumulator = new LongAccumulator((x,y) -> x+y ,0);

        LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left+right;
            }
        },0);
        accumulator.accumulate(1);
        System.out.println(accumulator.get());



    }
}
