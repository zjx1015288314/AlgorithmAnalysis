package com.zjx.ThreadTest;

public class MyRunnable implements Runnable {
    private final int i;

    public MyRunnable() {
        this.i = 1;
    }

    @Override
    public void run() {
        System.out.println("MyRunnable");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        try {
            Thread.sleep(2000);
            Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
