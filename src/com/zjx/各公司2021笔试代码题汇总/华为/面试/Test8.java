package com.zjx.各公司2021笔试代码题汇总.华为.面试;


import java.util.Stack;

/**
 * 1.两个栈实现一个队列
 * 2一桶球每次取一个或者三个 n个球有多少种取法4个球 1111  13  31三种取法
 */
public class Test8 {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
    }

    static class MyQueue {

        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        //FIFO
        public void offer(Integer i) {
            stack1.push(i);
        }

        public Integer poll() {
            if (isEmpty()) {
                throw new RuntimeException("queue is Empty");
            }

            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        private boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

}
