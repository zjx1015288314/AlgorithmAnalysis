package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.三种新建线程的方法;

/**
 * Thread: 继承方式, 不建议使用, 因为Java是单继承的，继承了Thread就没办法继承其它类了，不够灵活
 */
public class CreateThread1 {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("name : " + this.getName());
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
    }
}
