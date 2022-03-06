package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.静态代理;

public class UserDao implements IUserDao{

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
