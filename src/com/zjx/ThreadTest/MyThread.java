package com.zjx.ThreadTest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThread extends Thread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        //test Executor
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();   // shutdown() 方法会等待线程都执行完毕之后再关闭
        executorService.shutdownNow(); // shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        super.run();
        System.out.println("111");
    }
}
