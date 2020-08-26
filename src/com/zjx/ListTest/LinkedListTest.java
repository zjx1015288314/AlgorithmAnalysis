package com.zjx.ListTest;

import java.util.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        // 测试LinkedList的API
        testLinkedListAPIs();

        // 将LinkedList当作 LIFO(后进先出)的堆栈
        useLinkedListAsLIFO();

        // 将LinkedList当作 FIFO(先进先出)的队列
        useLinkedListAsFIFO();
    }

    /*
     * 测试LinkedList中部分API
     */
    private static void testLinkedListAPIs() {

        //一）数组准备工作
        String val = null;
        LinkedList llist = new LinkedList();
        //添加操作,依次添加1,2,3
        llist.add(1);    //LinkedList为泛型类,在声明时如果不指定泛型而使用原生类,则泛型使用泛型的上限(这里是Object)代替
        //所以这里看到的是add(Object e),而不是add(E e)
        llist.add(2);
        llist.add(3);
        //将“4”添加到第一个位置
        llist.add(1, 4);

        //二）第一次测试开始
        System.out.println("\nTest \" addFirst(), removeFirst(), getFirst() \" ");
        //(01)将“10”添加到第一个位置   失败的话,抛出异常
        llist.addFirst(10);
        System.out.println("llist: " + llist);
        //(02)将第一个元素删除   失败的话,抛出异常
        System.out.println("llist.removeFirst():" + llist.removeFirst());
        System.out.println("llist: " + llist);
        //(03)获取第一个元素   失败的话,抛出异常
        System.out.println("llist.getFirst():" + llist.getFirst());

        //三）第二次测试开始
        System.out.println("\nTest \" offerFirst(), pollFirst(), peekFirst() \" ");
        //(01)将“20”添加到第一个位置   失败的话,抛出异常
        llist.offerFirst(20);
        System.out.println("llist: " + llist);
        //(02)将第一个元素删除   失败的话,抛出异常
        System.out.println("llist.pollFirst():" + llist.pollFirst());
        System.out.println("llist: " + llist);
        //(03)获取第一个元素   失败的话,抛出异常
        System.out.println("llist.peekFirst():" + llist.peekFirst());

        //四）第三次测试开始
        System.out.println("\nTest \" addLast(), removeLast(), getLast() \" ");
        //(01)将“20”添加到最后一个位置   失败的话,抛出异常
        llist.addLast(10);
        System.out.println("llist: " + llist);
        //(02)将最后一个元素删除   失败的话,抛出异常
        System.out.println("llist.removeLast():" + llist.removeLast());
        System.out.println("llist: " + llist);
        //(03)获取最后一个元素   失败的话,抛出异常
        System.out.println("llist.getLast():" + llist.getLast());

        //五）第四次测试开始
        System.out.println("\nTest \" offerLast(), pollLast(), peekLast() \" ");
        //(01)将“20”添加到最后一个位置   失败的话,抛出异常
        llist.offerLast(20);
        System.out.println("llist: " + llist);
        //(02)将最后一个元素删除   失败的话,抛出异常
        System.out.println("llist.pollLast():" + llist.pollLast());
        System.out.println("llist: " + llist);
        //(03)获取最后一个元素   失败的话,抛出异常
        System.out.println("llist.peekLast():" + llist.peekLast());


        //以下是不建议的操作
        llist.set(2, 300);
        System.out.println("\nget(3): " + llist.get(3));

        //toArray(T[] a)
        Integer[] arr = (Integer[]) llist.toArray(new Integer[0]);
        for (int item : arr) {
            System.out.println("item: " + item);
        }

        //输出大小
        System.out.println(llist.size());

        //清空list
        llist.clear();

        //判断list是否为空
        System.out.println("isEmpty:" + llist.isEmpty() + "\n");

    }

    /**
     * 将LinkedList当作 LIFO(后进先出)的堆栈
     */
    private static void useLinkedListAsLIFO() {
        System.out.println("\nuseLinkedListAsLIFO");
        // 新建一个LinkedList
        LinkedList stack = new LinkedList();

        // 将1,2,3,4添加到堆栈中
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        // 打印“栈”
        System.out.println("stack:" + stack);

        // 删除“栈顶元素”
        System.out.println("stack.pop():" + stack.pop());

        // 取出“栈顶元素”
        System.out.println("stack.peek():" + stack.peek());

        // 打印“栈”
        System.out.println("stack:" + stack);
    }

    /**
     * 将LinkedList当作 FIFO(先进先出)的队列
     */
    private static void useLinkedListAsFIFO() {
        System.out.println("\nuseLinkedListAsFIFO");
        // 新建一个LinkedList
        LinkedList queue = new LinkedList();

        // 将10,20,30,40添加到队列。每次都是插入到末尾
        queue.add("10");
        queue.add("20");
        queue.add("30");
        queue.add("40");
        // 打印“队列”
        System.out.println("queue:" + queue);

        // 删除(队列的第一个元素)
        System.out.println("queue.remove():" + queue.remove());

        // 读取(队列的第一个元素)
        System.out.println("queue.element():" + queue.element());

        // 打印“队列”
        System.out.println("queue:" + queue);
    }

}
