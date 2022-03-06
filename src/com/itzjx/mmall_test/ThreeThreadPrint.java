package com.itzjx.mmall_test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaojiexiong
 * @create 2020/7/20
 * @since 1.0.0
 */
public class ThreeThreadPrint {
    private static Object lock = new Object();
    private static int order = 1;
    private static int num = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        num = in.nextInt();
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                while (num.get() > 0) {
//                    if (order == 1 && num.get() > 0) {
//                        System.out.print("a");
//                        order++;
//                    }
//                }
//            }
//        };
//        Thread thread2 = new Thread() {
//            @Override
//            public void run() {
//                while (num.get() > 0) {
//                    if (order == 2 && num.get() > 0) {
//                        System.out.print("l");
//                        order++;
//                    }
//                }
//            }
//        };
//        Thread thread3 = new Thread() {
//            @Override
//            public void run() {
//                while (num.get() > 0) {
//                    if (order == 3 && num.get() > 0) {
//                        System.out.println("i");
//                        num.decrementAndGet();   //需要在order=1之前执行，否则thread1会多打印一个a
//                        order = 1;
//                    }
//                }
//            }
//        };


        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (num > 0) {
                    synchronized (lock) {
                        if (num > 0 && order == 1) {
                            System.out.print("a");
                            order++;
                        }
                    }
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (num > 0) {
                    synchronized (lock) {
                        if (num > 0 && order == 2) {
                            System.out.print("l");
                            order++;
                        }
                    }
                }
            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                while (num > 0) {
                    synchronized (lock) {
                        if (num > 0 && order == 3) {
                            System.out.println("i");
                            order = 1;
                            num--;
                        }
                    }
                }
            }
        };
        thread1.start();
        thread2.start();
        thread3.start();
        test();
    }

    private static void test() {
        Math.min(1,2);
        CyclicBarrier cb = new CyclicBarrier(3);
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("thread1 is  wait");
                    cb.await();
                    System.out.println("thread1 is  running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("thread2 is  wait");
                    cb.await();
                    System.out.println("thread2 is  running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("thread3 is  wait");
                    cb.await();
                    System.out.println("thread3 is  running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
