package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.多线程并发编程;

public class Account {
    private int count;

    public void save() {
        count = count + 1;
    }

    public int getCount() {
        return count;
    }
}
