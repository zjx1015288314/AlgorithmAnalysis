package com.zjx.multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(CompletableFutureTest::queryCode);
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(CompletableFutureTest::fetchPrice);
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
//        Thread.sleep(2000);
        List<CompletableFuture<?>> li = new ArrayList<>();
        li.add(cfQuery);
        li.add(cfFetch);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(li.toArray(new CompletableFuture[0]));
    }

    static String queryCode() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
