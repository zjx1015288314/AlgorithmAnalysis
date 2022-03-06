package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.多线程并发编程;

import static java.lang.Thread.sleep;

public class test {
    public static void main(String[] args) throws InterruptedException{
        Account account = new Account();
        for(int i = 0; i < 100; i++) {
            new Thread(new SaveMoneyInConcurrentMode(account)).start();
        }
        sleep(500);
        System.out.print(account.getCount());
    }
}
