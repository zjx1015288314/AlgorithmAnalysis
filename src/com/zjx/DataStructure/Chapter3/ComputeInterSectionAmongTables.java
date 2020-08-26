package com.zjx.DataStructure.Chapter3;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 3.4给定两个已排序的表L1和L2,只使用基本的表操作计算L1 ∩ L2
 */
public class ComputeInterSectionAmongTables {
    //利用两个表已经排好序的性质,实现O(N)的时间复杂度
    //T的上界定位Comparable目的是为了使用compareTo进行比较
    public static <T extends Comparable<? super T>> List<T> intersection(List<T> L1, List<T> L2, List<T> Intersec) {
        ListIterator iterL1 = L1.listIterator();
        ListIterator iterL2 = L2.listIterator();
        T item1 = null, item2 = null;
        //可比较的前提是两个表中都有元素,否则交集就不成立
        if (iterL1.hasNext() && iterL2.hasNext()) {
            item1 = (T) iterL1.next();
            item2 = (T) iterL2.next();
        }
        //不管是ArrayList还是LinkedList都可以包含null,所以要进行Notnull检测
        while (item1 != null && item2 != null) {
            int compareResult = item1.compareTo(item2);
            if (compareResult == 0) {
                Intersec.add(item1);
                //两个迭代器各进一步
                item1 = iterL1.hasNext() ? (T) iterL1.next() : null;
                item2 = iterL2.hasNext() ? (T) iterL2.next() : null;
            } else if (compareResult < 0) {  //那个迭代器小就进入下次迭代
                item1 = iterL1.hasNext() ? (T) iterL1.next() : null;
            } else {
                item2 = iterL2.hasNext() ? (T) iterL2.next() : null;
            }
        }
        return Intersec;
    }

    public static void main(String[] args) {
        LinkedList l1 = new LinkedList();
        l1.add(1);
        l1.add(3);
        l1.add(5);
        l1.add(7);
        LinkedList l2 = new LinkedList();
        l2.add(2);
        l2.add(5);
        l2.add(6);
        l2.add(7);
        LinkedList intersec = new LinkedList();
        System.out.println(intersection(l1, l2, intersec));


    }
}
