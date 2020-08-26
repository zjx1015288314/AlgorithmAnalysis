package com.zjx.DataStructure.InheritDemo;

import java.util.Comparator;

public class Person<T> implements Cloneable {
    static int b = 1;

    T[] a;
//    T[] a = new T();   //等号右边是非法的,不能创建泛型类型的实例或者泛型数组对象
//    static T b ;     //static方法不可以引用类的类型变量

    public Person() {

    }

    public Person(T[] a) {
        this.a = a;
    }

    public T getItem() {
        return a[0];
    }

    public void setItem(T item) {
        a[0] = item;
    }

    void read(T[] a, Comparator<? super T> cmp) {
        //static方法不可以引用类的类型变量
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person();

        Person person1 = (Person) person.clone();
        System.out.println(person1);


    }


    //无论目标类是否实现了Cloneable接口，只要调用到了Object.clone()，比如通过super.clone()，那么就必须处理或者抛出
    //CloneNotSupportedException，因为Object.clone()有throws这个异常，有抛的就必然有接的。

    //Object.clone()按照如下步骤执行：
    //  (1) 检查执行此方法的当前类有没有应用Clonable接口，如果没有，抛出CloneNotSupportedException异常。
    //  (2) 如果当前类有应用Clonable接口，则为当前类创建一个新对象，并将原对象中的所有字段进行一次浅层拷贝（通过赋值进行）。所以如果一个目标类应用了Clonable接口但并未重写clone()方法，它“看起来”仍然可以克隆。为什么是“看起来”下面会解释。

    //为什么应用了Cloneable接口的类通常还必须重写一个public的clone()方法？这里有两个原因：
    //如果不重写，由于Object.clone()是proteced属性，所以这个clone()方法将无法在外部被调用，
    //更精确地说，无法在目标类之外的任何地方调用。这样就使得克隆失去了用武之地。
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
