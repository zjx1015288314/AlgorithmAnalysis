package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.消费者生产者;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ProduceConsumer {
    private final List<Object> list;
    Semaphore mutex = new Semaphore(1);
    Semaphore notEmpty = new Semaphore(0);
    Semaphore notFull = null;


    public ProduceConsumer(int count) {
        list = new LinkedList<Object>();
        notFull = new Semaphore(count);
    }

    public static void main(String[] args) {
        ProduceConsumer pro = new ProduceConsumer(4);
        for (int i = 0; i < 14; i++) {
            new Thread(new Consumer(pro)).start();
        }
        for (int i = 0; i < 14; i++) {
            new Thread(new Producer(pro)).start();
        }
    }

    public void produce() throws InterruptedException{
        try {
            notFull.acquire();
            mutex.acquire();
            list.add(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + list.size());
        } finally {
            mutex.release();
            notEmpty.release();
        }
    }

    public void consume() throws InterruptedException{
        try {
            notEmpty.acquire();
            mutex.acquire();
            list.remove(0);
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + list.size());
        } finally {
            mutex.release();
            notFull.release();
        }
    }
}

class Consumer implements Runnable {
    ProduceConsumer pro;

    public Consumer(ProduceConsumer pro) {
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

class Producer implements Runnable {
    ProduceConsumer pro;

    public Producer(ProduceConsumer pro) {
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
