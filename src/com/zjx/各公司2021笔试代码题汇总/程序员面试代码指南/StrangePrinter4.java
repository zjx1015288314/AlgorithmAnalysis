package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.util.concurrent.*;

/**
 * @author zhaojiexiong
 * @create 2020/7/20
 * @since 1.0.0
 */
public class StrangePrinter4 {
    private int max;
    private int status = 1; // AtomicInteger保证可见性，也可以用volatile
    private boolean oddFlag = true;

    public StrangePrinter4(int max) {
        this.max = max;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        StrangePrinter4 strangePrinter = new StrangePrinter4(100);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(strangePrinter.new MyPrinter("偶数Printer", 0));
        executorService.submit(strangePrinter.new MyPrinter("奇数Printer", 1));
        executorService.shutdown();

        //顺序执行task1 task2 task3
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Callable<Long> task1 = () -> {
            System.out.println("Task 1 executed at " + System.currentTimeMillis());
            return 1000L;
        };
        Callable<Long> task2 = () -> {
            System.out.println("Task 2 executed at " + System.currentTimeMillis());
            return 1000L;
        };
        Callable<Long> task3 = () -> {
            System.out.println("Task 3 executed at " + System.currentTimeMillis());
            return 1000L;
        };
        ScheduledFuture<Long> future1 = executor.schedule(task1, 0, TimeUnit.MILLISECONDS);
        //future1.get() will block the main thread until the result is available
        ScheduledFuture<Long> future2 = executor.schedule(task2, (long) future1.get(), TimeUnit.MILLISECONDS);
        ScheduledFuture<Long> future3 = executor.schedule(task3, (long) future2.get(), TimeUnit.MILLISECONDS);
    }

    class MyPrinter implements Runnable {
        private String name;
        private int type; // 打印的类型，0：代表打印奇数，1：代表打印偶数

        public MyPrinter(String name, int type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public void run() {
            if (type == 1) {
                while (status <= max) { // 打印奇数
                    if (oddFlag) {
                        System.out.println(name + " - " + status++); // 打印奇数
                        oddFlag = !oddFlag;
                    }
                }
            } else {
                while (status<= max) { // 打印偶数
                    if (!oddFlag) {
                        System.out.println(name + " - " + status++); // 打印奇数
                        oddFlag = !oddFlag;
                    }
                }
            }
        }
    }
}
