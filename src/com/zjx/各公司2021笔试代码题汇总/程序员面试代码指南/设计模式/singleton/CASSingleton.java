package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 用CAS的好处在于不需要使用传统的锁机制来保证线程安全。
 * 但是我们的实现方式中，用了一个for循环一直在进行重试。
 * 所以，这种方式有一个比较大的缺点在于，如果忙等待一直执行不成功(一直在死循环中)，会对CPU造成较大的执行开销。
 */
public class CASSingleton {

    private static final AtomicReference<CASSingleton> INSTANCE = new AtomicReference<>();

    private CASSingleton() {
    }

    public static CASSingleton getInstance() {
        for (;;) {
            CASSingleton singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new CASSingleton();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }

}
