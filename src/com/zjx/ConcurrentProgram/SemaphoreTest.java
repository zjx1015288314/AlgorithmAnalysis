package com.zjx.ConcurrentProgram;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

        private static Semaphore sh = new Semaphore(3);
        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                new Thread(() -> {
                    try {
                        sh.acquire();
                        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        System.out.println("Thread-" + (finalI + 1) + "获取运行权限:" + dateStr);
                        Thread.sleep(1000);
                        sh.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
}
