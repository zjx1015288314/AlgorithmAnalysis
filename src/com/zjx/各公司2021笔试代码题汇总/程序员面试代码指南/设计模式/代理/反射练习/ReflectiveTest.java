package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.反射练习;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectiveTest {
    public static void main(String[] args) throws Exception {
        //一.通过类的.class获取
        Class<AAA> clz = AAA.class;
        //或者通过类的完整路径获取,这个方法由于不能确定传入的路径是否正确,这个方法会抛ClassNotFoundException
        Class<AAA> clz1 = (Class<AAA>) Class.forName("com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.代理.反射练习.AAA");
        //或者new一个实例,然后通过实例的getClass()方法获取
        AAA a = new AAA();
        Class<AAA> clz2 = (Class<AAA>) a.getClass();

        AAA a_reflect = clz.newInstance();

        //二.获取类中私有静态带有方法签名的testAA方法,getMethod第一个参数为方法名,第二个参数为testA的参数类型数组
        Method method = clz.getDeclaredMethod("testAA",String.class,int.class); //也可以写为clz.getMethod("testAA", new Class[]{double.class,double.class});
        //如果方法mul是私有的private方法,按照上面的方法去调用则会产生异常NoSuchMethodException,这时必须改变其访问属性
        method.setAccessible(true);//私有的方法通过发射可以修改其访问权限
        // invoke 方法的第一个参数是被调用的对象,这里是静态方法故为null,第二个参数为给将被调用的方法传入的参数
        Object result = method.invoke(a_reflect, "zjx",19950710);
        System.out.println(result);

        //三.获取类中的非静态公有方法
        Method method_2 = clz.getMethod("testA");
        //这是实例方法必须在一个对象上执行
        Object result_2 = method_2.invoke(null);
        System.out.println(result_2);

        //四.静态属性和非静态属性的使用
        Field field = clz.getDeclaredField("j");
        Object returnValue = field.get(a_reflect);
        System.out.println(returnValue);
    }
}
