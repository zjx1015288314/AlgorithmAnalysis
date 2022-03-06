package com.zjx.JVMTest.staticTest;

public class Test_1 {
    public static void main(String[] args) {
        System.out.println(Test_1_B.str);
    }
}

class Test_1_A {
    public static String str = "A_str";

    static {
        System.out.println("A static Block");
    }
}

class Test_1_B extends Test_1_A{
    public static String str = "B_str";

    static {
        System.out.println("B static Block");
    }}
