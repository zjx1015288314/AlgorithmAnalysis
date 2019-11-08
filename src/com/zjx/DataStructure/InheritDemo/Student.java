package com.zjx.DataStructure.InheritDemo;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {


    //这两个方法不属于重载(重载只是方法名称相同,参数类型、参数顺序、参数个数不同，其中并不包含返回值类型)
    //但是在Class文件中,只要两个方法的描述符不是完全一致那两个方法就可以共存
//    public static void method(List<Person> list){
//        System.out.println("invoke method(List<Person> list)");
//        return;
//    }
    public static int method(List<Integer> list){
        System.out.println("invoke method(List<Student> list)");
        return 1;
    }
    static class DeadLoopClass{

        static{
            boolean flag = true;
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            while(flag){

            }
        }
    }

    public static void main(String[] args) {
        method(new ArrayList<Integer>());
        Person[] person = new Student[1];    //数组的协变性
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        long h = 4l;
        System.out.println(c == d);   //true
        System.out.println(e == f);   //fasle
        System.out.println(c == (a + b));   //true
        System.out.println(c.equals(a+b));  //true
        System.out.println(g == (a + b));   //true
        System.out.println(g.equals(a+b));  //fasle

    }

}
