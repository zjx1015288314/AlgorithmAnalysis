package com.zjx.各公司2021笔试代码题汇总.华为.面试;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 题目描述：
 设计并实现一个简易的线程池。线程池应支持以下功能：
 1.提交任务：可以提交一个任务给线程池执行。任务是一个实现了 Runnable 接口的任务。
 2.线程池大小：线程池在启动时，可以设置一个固定的线程池大小，并根据此大小来决定可以并发执行的任务数。
 3.任务队列：如果线程池中的线程都在执行任务时，后续的任务应当被放入一个任务队列中，等待有空闲线程时再执行。
 4.线程回收：线程池应能优雅地关闭并释放资源。当所有任务完成后，线程池应该停止接收新任务并关闭。
 5.并发访问控制：并发线程中需要对临界资源进行访问
 */
public class MyThreadPool {

    private static Object lock = new Object();
    private int size;
    private LinkedBlockingDeque<Runnable> linkedBlockingDeque;
    private ConcurrentHashMap<String, Thread> threads;

    public MyThreadPool() {
        this.linkedBlockingDeque = new LinkedBlockingDeque<>();
        this.threads = new ConcurrentHashMap<>();
    }

    public void start(int size) {
        this.size = size;

    }

    public void execute(Runnable runnable) {
//        Thread thread = getThread();
//        if (thread == null) {
            //
            linkedBlockingDeque.offer(runnable);
            return;
//        }
        // run runable
//        thread.start();
    }

    private Thread getThread() {
        for (Thread thread : threads.values()) {
            if (thread.isAlive()) { //todo
                return thread;
            }
        }
        return null;
    }

    public Runnable getR() {
        return linkedBlockingDeque.getFirst();
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool= new MyThreadPool();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Runnable r = myThreadPool.getR();

                }
            }
        });
    }

}
