package com.zjx.DataStructure.Charpter4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * p112 4.10 编写一个程序，列出一个目录中所有的文件和它们的大小
 */
public class FileList {
    public static void list(File f) {
        list(f, 0);
    }

    public static void list(File f, int depth) {
        printName(f, depth);
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file : files) {
                list(file, depth + 1);
            }
        }

    }

    public static void printName(File f, int depth) {
        for (int i = 0; i < depth; i++)
            System.out.print("  ");
        if (f.isDirectory())
            System.out.println("Dir: " + f.getName());
        else
            System.out.println(f.getName() + " " + f.length());
    }

    public static void main(String[] args) {
        File file = new File("C:/");
        list(file);
    }
}
