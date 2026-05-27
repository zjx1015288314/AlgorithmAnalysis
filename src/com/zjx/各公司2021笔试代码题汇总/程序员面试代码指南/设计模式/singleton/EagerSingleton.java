package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.singleton;

/**
 * 饿汉式
 * 优点：类加载时就创建实例、线程安全
 * 缺点：类一加载就创建实例（可能浪费）
 * @author zjx
 * @date 2026/5/14
 */
public class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance(){
        return INSTANCE;
    }

}
