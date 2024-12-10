package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.消费者生产者;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ProduceConsumer {
    private static final List<Object> list = new LinkedList<>();
    private static final Semaphore mutex = new Semaphore(1);
    private static final Semaphore notEmpty = new Semaphore(0);
    private static Semaphore notFull;

    public ProduceConsumer(int count) {
        notFull = new Semaphore(count);
    }

    public static void main(String[] args) {
        ProduceConsumer pro = new ProduceConsumer(4);
        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer()).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer()).start();
        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                notFull.acquire();
                // 互斥锁保证list.add是单线程执行
                mutex.acquire();
                list.add(new Object());
                System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release();
                notEmpty.release();
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                notEmpty.acquire();
                mutex.acquire();
                list.remove(0);
                System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release();
                notFull.release();
            }
        }
    }
}

