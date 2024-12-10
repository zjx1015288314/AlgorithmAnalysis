package com.zjx.multiThread;

public class MultiThreadTest {

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        Thread myThread1 = new Thread(myThread, "thread 1");
        Thread myThread2 = new Thread(myThread, "thread 2");
        Thread myThread3 = new Thread(myThread, "thread 3");
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }

}
class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("MyThread " + Thread.currentThread().getName() + " running");
    }
}
