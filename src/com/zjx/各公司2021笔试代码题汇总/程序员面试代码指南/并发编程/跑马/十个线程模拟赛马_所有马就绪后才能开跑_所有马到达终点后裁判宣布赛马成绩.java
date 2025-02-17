package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.跑马;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 10个线程模拟赛马，所有马就绪后才能开跑，所有马到达终点后裁判宣布赛马成绩
 */
public class 十个线程模拟赛马_所有马就绪后才能开跑_所有马到达终点后裁判宣布赛马成绩 {

    private static final List<String> results = Collections.synchronizedList(new ArrayList<>());
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
        @Override
        public void run() {

            System.out.println("所有马到达终点");
            for (String result : results) {
                System.out.println(result);
            }
        }
    });

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Horse("Horse" + i)).start();
        }

        System.out.println("所有马就绪，准备开跑");
        countDownLatch.countDown();
    }

    static class Horse implements Runnable {

        private final String name;

        public Horse (String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                countDownLatch.countDown();
                long workTime = (long) (Math.random() * 10000);
                Thread.sleep(workTime);
                results.add(name + " spend " + workTime + "ms 到达终点");

                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
