package com.zjx.DataStructure.Chapter3;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Josephus问题是下面的游戏：N个人编号从1到N，围坐成一个圆圈。从1号开始传递一个热土豆
 * 经过M次传递后拿着热土豆的人被清除离座，围坐的圆圈紧缩，由坐在被清除的人后面的人拿起热土豆继续进行游戏
 * 最后剩下的人取胜。因此，如果M=0和N=5，则游戏人依序被清除，5号游戏人获胜
 * 如果M=1和N=5，那么被清除的人的顺序是2，4，1，5，3号人获胜
 */
public class JosephusTest {
    /**
     * 首先，围绕问题来看，数据的结构必定是链状的，而且最好是一个环形的链。为了方便实现，直接采用LinkedList类
     * 从输出测试来看,时间是O(N2)O(N2)
     * 如果数据量一大，这个时间将会很大。原因在于我们的代码设计部分，我们每次调用remove时，都重新遍历了一遍表
     * 尽管对于LinkedList来说删除效率很高，但是LinkedList的查找效率是很低的，这样一来这个设计就与ArrayList实现的没有区别了
     *
     * @param m
     * @param linkedList
     */

    public static void josephus(int m, LinkedList<Integer> linkedList) {
        int i = 0;
        while (linkedList.size() > 1) {
            for (int j = 0; j < m; j++) {
                i++;
            }
            while (i >= linkedList.size())
                i = i - linkedList.size();
            linkedList.remove(i);    //LinkedList中有三个remove方法:
            //1)E remove(int):移除指定索引处的元素
            //2)boolean remove(Object o):找出列表中的符合o.equals(item)的item并移除
            //3)E remove():调用removeFirst()，属于队列功能
        }
        System.out.println("the winner is " + linkedList.get(0));

    }

    /**
     * 其实原理和上面是一致的，主要把越界时的操作和删除元素的操作转移到了迭代器中，通过重新获取迭代器
     * 来将迭代器从头开始（模拟环状的链表） 通过迭代器能够有效地利用LinkedList的删除效率高的特性
     * 因为迭代器的时间是O(1)，LinkedList的remove时间也是O(1)，整体的运行时间理论上是O(N)
     */


    public static void betterJosephus(int m, LinkedList<Integer> list) {
        Iterator<Integer> itr = list.iterator();
        int count = 0;
        while (list.size() > 1) {
            if (!itr.hasNext()) {   //当M过大导致迭代器迭代到尾部时,则重置迭代器使其从头部开始
                itr = list.iterator();
            }
            int temp = -1;
            while (itr.hasNext() && count++ <= m) {  //这里count++ <= m是因为iterator.remove是删除最后一个已返回的元素
                //所以cursor应该在定位的右边
                temp = itr.next();
            }
            if (count > m) {
                count = 0;
                // System.out.println(temp);
                itr.remove();
            }
        }
        System.out.println("winner is " + list.getFirst());
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i <= 128000; i++) {  // josephus:64697ms    betterJosephus:302ms
            linkedList.add(i);
        }
        long startTime = System.currentTimeMillis();
//        josephus(20,linkedList);
        betterJosephus(20, linkedList);
        long endTime = System.currentTimeMillis();
        System.out.println("the runtime is " + (endTime - startTime) + "ms");
    }
}
