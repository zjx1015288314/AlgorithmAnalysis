package com.zjx.DataStructure;

import java.util.Comparator;

public class Person<T> {

    T[] a;
//    T[] a = new T();   //等号右边是非法的,不能创建泛型类型的实例或者泛型数组对象
//    static T b ;     //static方法不可以引用类的类型变量

    public Person(){

    }
    public Person(T[] a){
        this.a = a;
    }
    public T getItem(){
        return a[0];
    }
    public void setItem(T item){
        a[0] = item;
    }
    void read(T[] a, Comparator<? super T> cmp){
        //static方法不可以引用类的类型变量
    }
}
