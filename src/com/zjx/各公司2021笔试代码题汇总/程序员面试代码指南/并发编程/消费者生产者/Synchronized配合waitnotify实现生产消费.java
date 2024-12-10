package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.消费者生产者;

public class Synchronized配合waitnotify实现生产消费 {
    private static final Object monitor = new Object();
    private static int count = 0;
    private static final int buffcount = 10;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer1()).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer1()).start();
        }
    }

    static class Consumer1 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                synchronized (monitor){
                    while (count == buffcount){
                        monitor.wait();
                    }
                    count++;
                    System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + count);
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Producer1 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                synchronized (monitor){
                    while (count == 0){
                        monitor.wait();
                    }
                    count--;
                    System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + count);
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


