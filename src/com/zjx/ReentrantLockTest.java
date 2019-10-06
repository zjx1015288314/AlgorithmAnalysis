package com.zjx;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static Lock fairLock = new ReentrantLock(true);
    private static Lock unfairLock = new ReentrantLock();


    public void fair() {
        System.out.println("fair version");
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Job(fairLock));
            thread.setName("" + i);
            thread.start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unfair() {
        System.out.println("unfair version");
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Job(unfairLock));
            thread.setName("" + i);
            thread.start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Job implements Runnable {
        private Lock lock;
        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    System.out.println("Lock by:"
                            + Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
//        test.fair();
        test.unfair();
    }
}
