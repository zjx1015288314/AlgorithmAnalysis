package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.三个线程循环打印ABC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class 三个线程循环打印123orABCReentrantLock {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final int WORKER_COUNT = 3;
    private static int countIdx = 0;

    public static void main(String[] args) {
        final List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < WORKER_COUNT; i++) {
            conditions.add(LOCK.newCondition());
            Worker worker = new Worker(i, conditions);
            worker.start();
        }
    }

    static class Worker extends Thread {

        private final int index;
        private final List<Condition> conditions;

        public Worker(int index, List<Condition> conditions) {
            super("Thread-" + index);
            this.index = index;
            this.conditions = conditions;
        }

        private void signalNext() {
            int nextIndex = (index + 1) % conditions.size();
            conditions.get(nextIndex).signal();
        }

        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    while (countIdx % conditions.size() != index) {
                        conditions.get(index).await();
                    }
                    if (countIdx >= 100) {
                        signalNext();
                        return;
                    }
                    System.out.println(this.getName() + " " + countIdx);
                    countIdx++;
                    signalNext();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

}
