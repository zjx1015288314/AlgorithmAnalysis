package com.zjx.ThreadTest;

import java.io.*;

public class MyClassLoder extends ClassLoader {
    private String name;

    public MyClassLoder(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        clazz = findClass(name);
        return clazz;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = null;
        InputStream ins = null;
        ByteArrayOutputStream baos = null;
        try {
            String myPath = "J:\\IDEA2019\\workspace\\AlgorithmAnalysis" +
                    "\\out\\production\\AlgorithmAnalysis\\" + name.replace(".", "\\") + ".class";
            System.out.println(myPath);
            ins = new FileInputStream(new File(myPath));
            baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ins.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass("AlgorithmAnalysis.out.production.AlgorithmAnalysis.com.zjx.ThreadTest.StringTest1", data, 0, data.length);
    }

//    private byte[] getClassData(String className) {
//        String path = classNameToPath(className);
//        try {
//            InputStream ins = new FileInputStream(path);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            int bufferSize = 4096;
//            byte[] buffer = new byte[bufferSize];
//            int bytesNumRead;
//            while ((bytesNumRead = ins.read(buffer)) != -1) {
//                baos.write(buffer, 0, bytesNumRead);
//            }
//            return baos.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private String classNameToPath(String className) {
//        return rootDir + File.separatorChar +
//                className.replace('.', File.separatorChar) + ".class";
//    }
}
