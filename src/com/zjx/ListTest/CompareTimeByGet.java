package com.zjx.ListTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class CompareTimeByGet {
    public static void main(String[] args) {
//        test1();
        test2();
    }
    public static void test1(){
        LinkedList linkedList = new LinkedList();
        for(int i = 0; i < 100000; i++){
            linkedList.add(i);
        }

        // 迭代器遍历,15ms左右
        long start = System.currentTimeMillis();
        Iterator iterator = linkedList.iterator();
        while(iterator.hasNext()){
            iterator.next();
        }
        long end = System.currentTimeMillis();
        System.out.println("Iterator："+ (end - start) +"ms");

        // 顺序遍历(随机遍历),5-6s
        long start1 = System.currentTimeMillis();
        for(int i = 0; i < linkedList.size(); i++){
            linkedList.get(i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("for ："+ (end1 - start1) +"ms");

        //增强的for循环,15ms左右
        long start2 = System.currentTimeMillis();
        for(Object  i : linkedList);
        long end2 = System.currentTimeMillis();
        System.out.println("增强for ："+ (end2 - start2) +"ms");

        //removeFirst,2-3ms
        long start3 = System.currentTimeMillis();
        while(linkedList.size() != 0){
            linkedList.removeFirst();
        }
        long end3 = System.currentTimeMillis();
        System.out.println("removeFirst ："+ (end3 - start3) +"ms");
    }

    public static void test2(){
        ArrayList arrayList = new ArrayList();
        for(int i = 0; i < 100000; i++){
            arrayList.add(i);
        }

        //for循环,2-3ms左右
        long start = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("for  ："+ (end - start) +"ms");

        //迭代遍历,10-15ms左右
        long start1 = System.currentTimeMillis();
        Iterator iterable = arrayList.iterator() ;
        while (iterable.hasNext()){
            iterable.next();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Iterator    ："+ (end1 - start1) +"ms");

        //增强的for循环,2-3ms左右
        long start2 = System.currentTimeMillis();
        for(Object  i : arrayList);
        long end2 = System.currentTimeMillis();
        System.out.println("增强for ："+ (end2 - start2) +"ms");
    }

}
