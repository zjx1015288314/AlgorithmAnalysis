package com.zjx.DataStructure.Chapter3;

import java.util.*;

public class CombineTwoTables {

    public static <T extends Comparable<? super T>> LinkedList<T> combine(LinkedList<T> L1, LinkedList<T> L2, LinkedList<T> result) {
        ListIterator<T> iterL1 = L1.listIterator();
        ListIterator<T> iterL2 = L2.listIterator();
        T item1 = null, item2 = null;
        if (!iterL1.hasNext())
            return L2;
        else if (!iterL2.hasNext())
            return L1;
        else {
            item1 = iterL1.next();
            item2 = iterL2.next();
        }
        while (item1 != null && item2 != null) {
            int compareResult = item1.compareTo(item2);
            if (compareResult == 0) {
                result.add(item1);
                //两个迭代器各进一步
                item1 = iterL1.hasNext() ? iterL1.next() : iterL2.hasNext() ? iterL2.next() : null;
                item2 = iterL2.hasNext() ? iterL2.next() : iterL1.hasNext() ? iterL1.next() : null;
            } else if (compareResult < 0) {  //哪个迭代器小就进入下次迭代
                result.add(item1);
                item1 = iterL1.hasNext() ? iterL1.next() : item2 != null ? item2 : null;
            } else {
                result.add(item2);
                item2 = iterL2.hasNext() ? iterL2.next() : item1 != null ? item1 : null;
            }
        }

//        //当结束时,iteral1还未迭代完成
//        while (item1 != null) {
//            result.add(item1);
//            item1 = iterL1.hasNext() ? iterL1.next() : null;
//        }
//        //当结束时,iteral2还未迭代完成
//        while (item2 != null) {
//            result.add(item2);
//            item2 = iterL2.hasNext() ? iterL2.next() : null;
//        }
        return result;
    }

    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        for (int i = 0; i < 1280000; i++) {
            l1.add(i);
            l2.add(i + 1);
        }
        LinkedList<Integer> result = new LinkedList<>();
        long starttime = System.currentTimeMillis();
        combine(l1, l2, result);
        long endtime = System.currentTimeMillis();
        System.out.println("the runtime is " + (endtime - starttime) + " ms");

//        for (int i = 0; i <result.size() ; i++) {
//            System.out.println(result.get(i));
//        }

    }
}
