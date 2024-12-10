package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.消费者生产者;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {
    private static int count = 0;  //当前的生产数量
    private static final int buffCount = 10;  //最大允许的生产数量
    private static final ReentrantLock lock = new ReentrantLock();
    //创建两个条件变量，一个为缓冲区非满，一个为缓冲区非空
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new MyConsumer()).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new MyProducer()).start();
        }
    }

    static class MyConsumer implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                while (count == 0) {
                    notEmpty.await();
                }
                count--;
                System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + count);
                notFull.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    static class MyProducer implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                while (count == buffCount) {
                    // 降低生产者对锁的竞争频率
                    Thread.sleep(1000);
                    notFull.await();
                }
                count++;
                System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + count);
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}


