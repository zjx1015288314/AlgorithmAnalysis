package com.zjx.DataStructure.Chapter3;

public class SingleLinkedListStack<T> {
    /**
     * 3.31 使用单链表来实现栈,不用头结点和尾结点
     * <p>
     * 功能描述：java栈的链表实现方式
     * 编程思想：链表栈中包括结点类和栈顶结点，初始化时将栈顶结点指向null
     * 1、添加结点时将新加入的结点指向原本的栈顶结点，将新的栈顶结点指向新插入的节点
     * 2、出栈时栈顶出栈并将新栈顶结点指向原栈顶结点
     * </p>
     */
    private Node<T> top;

    public SingleLinkedListStack() {
        top = null;
    }

    private class Node<T> {
        private T data;
        private Node<T> next;

        Node() {
            this(null, null);
        }

        Node(T data) {
            this(data, null);
        }

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    //入栈
    public void push(T item) {
        Node<T> node = new Node<>(item, top);
        top = node;
    }

    //出栈
    public T pop() throws Exception {
        if (top == null)
            throw new Exception("栈为空,元素不可出栈!!");
        T item = (T) top.data;
        Node<T> node = top;
        top = top.next;
        node.next = null;
        node.data = null;  //help GC,防止内存泄漏
        return item;
    }

    //遍历打印栈
    public void printStack() {
        Node<T> node = top;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    public static void main(String[] args) throws Exception {
        SingleLinkedListStack<String> stack = new SingleLinkedListStack<String>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.printStack();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }

}
