package com.itzjx.mmall_test;

/**
 * @author zhaojiexiong
 * @create 2020/5/19
 * @since 1.0.0
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("t1"));
        Thread t2 = new Thread(() -> {
            try {
                //引用t1线程，等待t1线程执行完
                Thread.sleep(10000);
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });
        Thread t3 = new Thread(() -> {
            try {
                //引用t2线程，等待t2线程执行完
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3");
        });

            t3.start();
            t2.start();
            t1.start();
    }
}
