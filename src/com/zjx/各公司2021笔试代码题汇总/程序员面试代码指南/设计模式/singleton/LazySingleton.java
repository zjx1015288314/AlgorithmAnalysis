package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.singleton;

/**
 * 懒汉式  需要加synchronized 不加的话是线程不安全的
 * 优点：线程安全
 * 缺点：每次调用都加锁 → 性能差
 * @author zjx
 * @date 2026/5/14
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){}

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
