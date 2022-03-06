package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.消费者生产者;

public class Synchronized配合waitnotify实现生产消费 {
    private static final Object monitor = new Object();
    private static int count = 0;
    private static final int buffcount = 10;

    public static void main(String[] args) {
        Synchronized配合waitnotify实现生产消费 pro = new Synchronized配合waitnotify实现生产消费();
        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer1(pro)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer1(pro)).start();
        }
    }

    public void produce() throws InterruptedException{
        synchronized (monitor){
            while (count == buffcount){
                monitor.wait();
            }
            count++;
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + count);
            monitor.notifyAll();
        }
    }

    public void consume() throws InterruptedException{
        synchronized (monitor){
            while (count == 0){
                monitor.wait();
            }
            count--;
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + count);
            monitor.notifyAll();
        }
    }
}

class Consumer1 implements Runnable {
    Synchronized配合waitnotify实现生产消费 pro;

    public Consumer1(Synchronized配合waitnotify实现生产消费 pro) {
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

class Producer1 implements Runnable {
    Synchronized配合waitnotify实现生产消费 pro;

    public Producer1(Synchronized配合waitnotify实现生产消费 pro) {
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
