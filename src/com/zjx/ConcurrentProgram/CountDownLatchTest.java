package com.zjx.ConcurrentProgram;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(5);
        StringBuffer sb = new StringBuffer(255);
        Thread waitThread1 = new Thread(() -> {
            try {
                sb.append("waitThread1 等待任务就绪;\n");
                latch.await();
                sb.append("waitThread1 等待结束;\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread waitThread2 = new Thread(() -> {
            try {
                sb.append("waitThread2 等待任务就绪;\n");
                latch.await();
                sb.append("waitThread2 等待结束;\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        waitThread1.start();
        waitThread2.start();

        for (int i = 0; i < 5; i++) {
            final int tmp = i;
            Thread task = new Thread(() -> {
                sb.append(String.format("task[%d] 准备就绪;\n",tmp));
                latch.countDown();
                sb.append(String.format("task[%d] 结束;\n",tmp));
            });
            task.start();
        }
        //即join()的作用是：“等待该线程终止”，
        // 这里需要理解的就是该线程是指的主线程等待子线程的终止
        //也就是 在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行,在这里应该是为了配合打印sb，否则sb会在线程终止前输出
        waitThread1.join();
        waitThread2.join();
        sb.append("test结束;\n");
        System.out.println(sb.toString());

    }
}
