package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.三个线程循环打印ABC;

public class 三个线程循环打印123orABCSynchronized {

    private static final Object LOCK = new Object();
    private static int count = 0;
    private static final int MAX_PRINT_COUNT = 100;

    public static void main(String[] args) {
        new Thread(new Seq(0)).start();
        new Thread(new Seq(1)).start();
        new Thread(new Seq(2)).start();
    }

    static class Seq implements Runnable {

        private final int index;

        public Seq(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            while (count < MAX_PRINT_COUNT) {
                synchronized (LOCK) {
                    try {
                        while (count % 3 != index) {
                            LOCK.wait();
                        }
                        if (count <= MAX_PRINT_COUNT) {
                            char c = (char) ('A' + index);
                            System.out.println("Thread-" + index + ": " + c + " count: " + count);
                        }
                        count++;
                        LOCK.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
