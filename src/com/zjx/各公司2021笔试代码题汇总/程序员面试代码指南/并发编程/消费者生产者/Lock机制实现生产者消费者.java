package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.消费者生产者;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock机制实现生产者消费者 {
    private static int count = 0;  //当前的生产数量
    private static final int buffCount = 10;  //最大允许的生产数量
    private static Lock lock = new ReentrantLock();

    //创建两个条件变量，一个为缓冲区非满，一个为缓冲区非空
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        Lock机制实现生产者消费者 pro = new Lock机制实现生产者消费者();
        for (int i = 0; i < 10; i++) {
            new Thread(new MyProducer(pro)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new MyConsumer(pro)).start();
        }

    }

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            while (count == buffCount) {
                notFull.await();
            }
            count++;
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + count);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            count--;
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + count);
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class MyConsumer implements Runnable {
    Lock机制实现生产者消费者 pro;

    public MyConsumer(Lock机制实现生产者消费者 pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            pro.consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyProducer implements Runnable {
    Lock机制实现生产者消费者 pro;

    public MyProducer(Lock机制实现生产者消费者 pro) {
        this.pro = pro;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            pro.produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
