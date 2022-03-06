package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.cglib动态代理;

/**
 * 需要导入cglib相关的包
 * 目标对象只是一个单独的对象,并没有实现任何的接口,
 * 这个时候就可以使用以目标对象子类的方式类实现代理,这种方法就叫做:Cglib代理
 * 需注意：
 *  1.代理的类不能为final,否则报错
 *  2.目标对象的方法如果为final/static/private,那么就不会被拦截,即不会执行目标对象额外的业务方法.
 */
//public class ProxyFactory implements MethodInterceptor {
//    //维护目标对象
//    private Object target;
//
//    public ProxyFactory(Object target) {
//        this.target = target;
//    }
//
//    //给目标对象创建一个代理对象
//    public Object getProxyInstance(){
//        //1.工具类
//        Enhancer en = new Enhancer();
//        //2.设置父类
//        en.setSuperclass(target.getClass());
//        //3.设置回调函数
//        en.setCallback(this);
//        //4.创建子类(代理对象)
//        return en.create();
//
//    }
//
//    @Override
//    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        System.out.println("开始事务...");
//
//        //执行目标对象的方法
//        Object returnValue = method.invoke(target, args);
//
//        System.out.println("提交事务...");
//
//        return returnValue;
//    }
//}
