package com.zjx.DataStructure.Chapter3;

import java.util.Scanner;

public class ScannerTest {
    /**
     * Scanner是一个扫描器，我们录取到键盘的数据(已回车作为输入结束的标志)，先存到缓存区等待读取
     * 它判断读取结束的标示是  空白符；比如空格，回车，tab 等等
     * next()方法读取到空白符就结束；
     * nextLine()读取到回车结束也就是“\r”；
     * @param args
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("请输入字符串（next）：");
        String str = input.next();
        System.out.println(str);
        System.out.println("请输入字符串（nextLine）：");
        String str1 = input.nextLine();
        System.out.println(str1);

        /*
        结果：
            请输入字符串（next）：
            12 /t
            12
            请输入字符串（nextLine）：
            /t
        */
    }
}
