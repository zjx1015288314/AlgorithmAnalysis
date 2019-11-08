package com.zjx.DataStructure.Chapter3;

import com.zjx.ListTest.MyArrayList;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 3.9 对比Collection.addAll的LinkedList和ArrayList实现,写出给定的addAll(Iterable<? extends E> items)的实现
 */
public class addAllImpl<E> {
    public void addAll(Iterable<? extends E> item){
        MyArrayList myArrayList = new MyArrayList<>();
        Iterator<? extends E> iterator = item.iterator();
        while(iterator.hasNext())
            myArrayList.add(iterator.next());
    }


    /**
     * 在ArrayList中是通过底层数组扩容和System.arraycopy(a, 0, elementData, s, numNew);来完成的
     */

    /**
     * 在LinkedList中是通过for循环内链接前后链表来完成的
     */
//    LinkedList.add(int,Collection<<? extends E>)
//    public boolean addAll(int index, Collection<? extends E> c) {
//        /** 此处检查index是否越界**/
//
//        Object[] a = c.toArray();
//        int numNew = a.length;
//        if (numNew == 0)
//            return false;
//
//        LinkedList.Node<E> pred, succ;  //两个工具元素
//        if (index == size) {  //尾部检查
//            succ = null;
//            pred = last;
//        } else {
//            succ = node(index);
//            pred = succ.prev;
//        }
//        //此处的for循环就像上链条一样,先把链条断开,再把新的每一节链接到前面的链条上,最后与后面的链条链接
//        for (Object o : a) {
//            @SuppressWarnings("unchecked") E e = (E) o;
//            LinkedList.Node<E> newNode = new LinkedList.Node<>(pred, e, null);
//            if (pred == null)
//                first = newNode;
//            else
//                pred.next = newNode;
//            pred = newNode;
//        }
//        //与后面的链条链接    分为是否是尾部两种情况
//        if (succ == null) {
//            last = pred;
//        } else {
//            pred.next = succ;
//            succ.prev = pred;
//        }
//
//        size += numNew;
//        modCount++;
//        return true;
//    }
}
