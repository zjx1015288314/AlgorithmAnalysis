package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.singleton;

import java.io.Serializable;

/**
 * @author zhaojiexiong
 * @create 2020/7/22
 * @since 1.0.0
 */
public class DCLSingleton implements Serializable,Cloneable {
    private static volatile DCLSingleton singleton = null;

    private DCLSingleton(){}

    public static DCLSingleton getInstance(){
        if(singleton == null){
            synchronized (DCLSingleton.class){  //类级别的锁
                if(singleton == null){
                    singleton = new DCLSingleton();
                }
            }
        }
        return singleton;
    }

   private Object readResolve(){
        return singleton;
   }
}
