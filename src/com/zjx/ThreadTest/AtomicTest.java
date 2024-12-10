package com.zjx.ThreadTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

public class AtomicTest {

    static AtomicInteger num = new AtomicInteger(1);

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        for (int i = 0; i < 10000; i++) {
            new Thread(myRunnable, "Thread" + i).start();
        }
    }

    static class MyRunnable implements Runnable {
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicStampedReference<Integer> re = new AtomicStampedReference<>(1, 1);
        LongAdder longAdder = new LongAdder();
        @Override
        public void run() {
            synchronized (atomicInteger) {
                if (atomicInteger.get() >= 9999) {
                    return;
                }
                atomicInteger.incrementAndGet();
                longAdder.add(1);
                re.compareAndSet(1, 2, 1, 2);
                System.out.println(Thread.currentThread().getName() + ": **" + atomicInteger + "**");
            }

        }
    }

}
