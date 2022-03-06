package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.singleton;

/**
 * @author zhaojiexiong
 * @create 2020/7/22
 * @since 1.0.0
 */
public class Singleton {
        private Singleton(){}

        private static class InstanceHolder{
            private final static Singleton INSTANCE = new Singleton();
        }

        public static Singleton getInstance(){
            return InstanceHolder.INSTANCE;
        }
}
