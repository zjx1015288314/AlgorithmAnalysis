package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.静态代理;

/**
 * 静态代理的思想：代理类UserDaoProxy与被代理类UserDao实现同样的接口，并且在代理类中
 * 持有被代理对象的引用，通过同一个方法进行增强
 * 优点：可以做到在不修改目标对象的功能前提下,对目标功能扩展.
 * 缺点:因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,
 * 目标对象与代理对象都要维护.如何解决静态代理中的缺点呢?答案是可以使用动态代理方式
 */
public class UserDaoProxy {
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target = target;
    }
    public void save(){
        System.out.println("预处理");
        target.save();  //执行目标对象方法
        System.out.println("后置处理");
    }
}
