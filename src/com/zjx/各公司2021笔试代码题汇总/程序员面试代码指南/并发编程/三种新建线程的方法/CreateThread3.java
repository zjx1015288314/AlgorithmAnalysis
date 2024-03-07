package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.三种新建线程的方法;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable: Thread和Runnable都是重写的run()方法并且没有返回值，Callable是重写的call()
 * 方法并且有返回值并可以借助FutureTask类来判断线程是否已经执行完毕或者取消线程执行
 *
 * https://blog.csdn.net/vbirdbest/article/details/81282163
 */
public class CreateThread3 {

    static class MyCallable implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            Integer res = 111;
            return res;
        }
    }
    public static void main(String[] args) {
        MyCallable callable = new MyCallable();
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread thread1 = new Thread(task);
        thread1.start();
        try {
            int res = task.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
