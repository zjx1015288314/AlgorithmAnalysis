package com.zjx.ThreadTest;

public class ThreadContextClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoder classLoder = new MyClassLoder(MyClassLoder.class.getClassLoader(),
                "MyClassLoder");
        Class clazz = classLoder.loadClass("com.zjx.ThreadTest.StringTest");
        java.lang.Object o = clazz.newInstance();
        System.out.println(o.getClass().getClassLoader().toString());

    }


}
