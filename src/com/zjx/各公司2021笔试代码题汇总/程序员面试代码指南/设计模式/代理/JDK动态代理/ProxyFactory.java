package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.JDK动态代理;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.静态代理.IUserDao;
import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.静态代理.UserDao;

import java.lang.reflect.Proxy;

/**
 * 创建动态代理对象
 * 动态代理不需要实现接口,但是需要指定接口类型
 * 动态代理有以下特点:
 * 1.代理对象,不需要实现接口,但是目标对象一定要实现接口,否则不能用JDK动态代理
 * 2.代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
 * 3.动态代理也叫做:JDK代理,接口代理
 */
public class ProxyFactory {
    //具体步骤：接口类IUserDao.java以及接口实现类,目标对象UserDao是一样的,没有做修改.
    //在这个基础上,增加一个代理工厂类(ProxyFactory.java),将代理类写在这个地方,
    //然后在测试类(需要使用到代理的代码)中先建立目标对象和代理对象的联系,然后代用代理对象的中同名方法

    //维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), (proxy, method, args) -> {
                    System.out.println("预处理");
                    //执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("后置处理");
                    return returnValue;
                });
    }

    public static void main(String[] args) {
        //生成目标对象
        IUserDao target = new UserDao();
        //给目标对象创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        //执行代理对象的方法
        proxy.save();
    }
}
