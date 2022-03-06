package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.三种新建线程的方法;

/**
 *  Runnable: 实现接口，比Thread类更加灵活，没有单继承的限制
 */
public class CreateThread2 {
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("name: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();
        new Thread(runnable,"线程1").start();
    }

}
