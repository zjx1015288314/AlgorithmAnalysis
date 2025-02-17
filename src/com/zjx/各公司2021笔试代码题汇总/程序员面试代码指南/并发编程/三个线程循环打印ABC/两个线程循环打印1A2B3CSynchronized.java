package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.三个线程循环打印ABC;

import java.util.concurrent.Callable;

public class 两个线程循环打印1A2B3CSynchronized {

    private static final Object LOCK = new Object();
    private static boolean printNumber = true;

    public static void main(String[] args) {
        new Thread(new NumberPrinter()).start();
        new Thread(new LetterPrinter()).start();
    }

    static class NumberPrinter implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                synchronized (LOCK) {
                    if (!printNumber) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(i);
                    printNumber = false;
                    LOCK.notify();
                }
            }
        }
    }

    static class LetterPrinter implements Runnable {

        @Override
        public void run() {
            for (char c = 'A'; c <= 'C'; c++) {
                synchronized (LOCK) {
                    if (printNumber) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(c);
                    printNumber = true;
                    LOCK.notify();
                }
            }
        }
    }

}
