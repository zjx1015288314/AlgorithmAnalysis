package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.三个线程循环打印ABC;

public class 三个线程循环PrintABC {
    final Object monitor = new Object();
    volatile int count = 0;  //当前在第几次打印
    int printCount;  //循环打印多少次
    String id = "A";

    public 三个线程循环PrintABC(int printCount) {
        this.printCount = printCount;
    }

    public void printA() throws InterruptedException {
        while (count < printCount) {
            synchronized (monitor) {
                while (id != "A") {
                    monitor.wait();
                }
                if (count >= printCount) {
                    break;
                }
                System.out.println("A");
                id = "B";
                monitor.notifyAll();
            }
        }
    }

    public void printB() throws InterruptedException {
        while (count < printCount) {
            synchronized (monitor) {
                while (id != "B") {
                    monitor.wait();
                }
                if (count >= printCount) {
                    break;
                }
                System.out.println("B");
                id = "C";
                monitor.notifyAll();
            }
        }
    }

    public void printC() throws InterruptedException {
        while (count < printCount) {
            synchronized (monitor) {
                while (id != "C") {
                    monitor.wait();
                }
                System.out.println("C");
                id = "A";
                count++;
                monitor.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        三个线程循环PrintABC printABC = new 三个线程循环PrintABC(3);
        Thread threadA = new Thread(() -> {
            try {
                printABC.printA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                printABC.printB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                printABC.printC();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
