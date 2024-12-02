package com.zjx.ThreadTest;

import java.lang.reflect.InvocationTargetException;

public class ThreadContextClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoder classLoder = new MyClassLoder(MyClassLoder.class.getClassLoader(),
                "MyClassLoder");
        Class<?> clazz = classLoder.loadClass("com.zjx.ThreadTest.StringTest");
        java.lang.Object o = clazz.getDeclaredConstructor().newInstance();
        System.out.println(o.getClass().getClassLoader().toString());

    }


}
