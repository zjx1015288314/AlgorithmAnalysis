package com.zjx.ThreadTest;

import com.zjx.ConcurrentProgram.ReentrantLockTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptExample {
    private static class MyThread1 extends Thread {
        //通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛
        //出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
        @Override
        public void run() {
//            try {
//                Thread.sleep(2000);
//                System.out.println("Thread run");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println("Thread exception");
//
//            }
            System.out.println("Thread run");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread1();
        thread1.start();
        thread1.interrupt();
        System.out.println("Main run");
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lockInterruptibly();
        new Object().wait();
    }
}

