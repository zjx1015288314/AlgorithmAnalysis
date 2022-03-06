package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.singleton;

public class StaticInnerImpl {
    private StaticInnerImpl() {}

    private static class StaticInnerImplHolder {
        private static final StaticInnerImpl INSTANCE = new StaticInnerImpl();
    }

    public static StaticInnerImpl getInstance() {
        return StaticInnerImplHolder.INSTANCE;
    }
}
