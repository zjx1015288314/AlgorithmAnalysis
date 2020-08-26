package com.zjx.ThreadTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicTest {

    static AtomicInteger num = new AtomicInteger(1);
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    System.out.println(num.getAndIncrement());
                }
            };
            thread.start();
        }
    }
}
