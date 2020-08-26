package com.zjx.ConcurrentProgram;

import com.zjx.DataStructure.InheritDemo.Employee;
import com.zjx.DataStructure.InheritDemo.Person;
import com.zjx.DataStructure.InheritDemo.Student;

import java.math.BigInteger;
import java.util.Arrays;


public final class TheThirdLab {
    public static void main(String[] args) {
        String str = "0,1,2,3,4,5";
        String[] arr = str.split(","); // 用,分割
        System.out.println(Arrays.toString(arr)); // [0, 1, 2, 3, 4, 5]


//        int[] testlength = new int[5];
//        testlength[0]=1;
//        System.out.println(testlength.length);   //5

        char[] c = str.toCharArray();
        StringBuilder result = new StringBuilder();
        result.append(c[0]);
        int j;
        char[] c4 = new char[]{'a', 'd', 's', 'a', 'd', 's'};
        System.out.println(new String(c4));

        Person arr1 = new Employee();
        Person arr2 = new Student();
        Person arr3 = new Person();

        Employee em1 = (Employee) arr1;
//        Employee em2 = (Employee) arr2;   //运行期发生ClassCastException
//        Employee em3 = (Employee) arr3;    //运行期发生ClassCastException


        Person<Employee> person = new Person<>(new Employee[5]);
//        person.setItem(new Student());      //执行编译期检查，提早发现错误，泛型比Object数组更方便

//        Person<Person> person1 = new Person<Student>();   //将发生编译期异常，因为泛型是不变的.与数组不同，数组是协变类型的
        Person<? extends Person> person2 = new Person<Student>();
        Person<? super Student> person3 = new Person<Person>();


        Person<Integer> cell1 = new Person<>(new Integer[5]);
        cell1.setItem(4);
        Object cell = cell1;
        Person<String> cell2 = (Person<String>) cell;   //这里不会产生错误，因为cell1-cell-cell2是一个
        // 由Person-Object-Person的过程(泛型在编译期已经擦除,instanceof类型检测只对原始类进行)，符合类型转换，
        // 只有在取元素时才会检测a[0]的数据类型
//        String s = cell2.getItem();      //产生运行时错误

        BigInteger a = new BigInteger("10"), b = new BigInteger("3");
        BigInteger d = a.mod(b);
        System.out.println(d);
    }

}