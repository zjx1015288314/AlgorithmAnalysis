package com.zjx;

public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    threadLocal.set(i);
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                threadLocal.remove();   //ThreadLocalMap中Entry的键是弱引用,即使不使用remove显式移除,
                                        //ThreadLocal对象也会被JVM回收,而会通过set(ThreadLocal<?> key, Object value),
                                        //replaceStaleEntry(key, value, i),expungeStaleEntries()将键为null的Entry赋为null
            }
        }, "threadLocal1").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                threadLocal.remove();
            }
        }, "threadLocal2").start();
    }
}
