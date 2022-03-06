package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.反射练习;

public class AAA {
    static int i = 1;
    int j = 2;

    static public int testA(){
        i++;
        return i;
    }

    private int testAA(String s,int tmp){
        j++;
        System.out.println(s + tmp);
        return j;
    }

}
