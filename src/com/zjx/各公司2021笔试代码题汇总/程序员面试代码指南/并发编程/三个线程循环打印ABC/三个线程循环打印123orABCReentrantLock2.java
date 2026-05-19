package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.三个线程循环打印ABC;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class 三个线程循环打印123orABCReentrantLock2 {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final List<Condition> CONDITIONS = new ArrayList<>();
    private static final int totalCount = 100;
    private static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            CONDITIONS.add(LOCK.newCondition());
            new Thread(new Seq(i)).start();
        }
    }

    public static class Seq implements Runnable {

        private final int index;

        public Seq(int index) {
            this.index = index;
        }


        @Override
        public void run() {
            while (count <= totalCount) {
                LOCK.lock();

                try {
                    while (count % 3 != index) {
                        CONDITIONS.get(index).await();
                    }
                    if (count <= totalCount) {
                        char c = (char)('A' + count % 3);
                        System.out.println("Thread " + index + " : " + c + " count: " + count);
                    } else {
                        for (Condition condition : CONDITIONS) {
                            condition.signal();
                        }
                        break;
                    }
                    count++;
                    CONDITIONS.get((index + 1) % 3).signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

}
