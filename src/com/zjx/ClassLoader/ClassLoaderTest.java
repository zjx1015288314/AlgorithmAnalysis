package com.zjx.ClassLoader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null)
                        return super.loadClass(name);
                    byte[] b = new byte[is.available()];  //InputStream的此方法永远返回0
                    is.read(b);   //将输入流中的数据读入byte数组,read()返回int类型数据(值为0-255),如果结束则返回-1
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.zjx.ClassLoader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.zjx.ClassLoader.ClassLoaderTest);


    }
}
