package com.zjx.DataStructure.InheritDemo;

public class StaticDispatch {
    static abstract class Human {
    }

    class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        Man man = sr.new Man();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}
