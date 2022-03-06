package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static void main(String[] args) {
        StrangePrinter4 strangePrinter = new StrangePrinter4(100);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(strangePrinter.new MyPrinter("偶数Printer", 0));
        executorService.submit(strangePrinter.new MyPrinter("奇数Printer", 1));
        executorService.shutdown();
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
