package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.多线程并发编程;

public class SaveMoneyInConcurrentMode implements Runnable{
    private Account account;

    public SaveMoneyInConcurrentMode(Account account) {
        this.account = account;
    }

    @Override
    public void run(){
        synchronized (account) {
            account.save();
        }
    }
}
