package com.zjx.DataStructure.Chapter3;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> {
    /**
     * 用数组写一个栈
     * 底层用Object数组来实现,栈带有泛型,最重要的出栈入栈,入栈直接在数组尾部添加元素,出栈则从数组尾部取出元素
     * 需要一个光标来标记数组尾部以便快速存取;为了保证出入栈的成功执行,需要添加isFull(),isEmpty()来检测数组是否
     * 满或空,否则抛出异常.同时还应有扩容策略.为了简便,就不提供并发安全性
     */
    private Object[] elementData;
    private int top;  //光标初始化-1
    private int elementCount;
    public ArrayStack(){
        this(10);
    }
    public ArrayStack(int capacity){
        elementData = new Object[capacity];
//        top = -1;
    }

    //入栈
    public void push(T item){
        if (!isFull()) {
            elementData[elementCount++] = item;
        }else{
            elementData = grow();
            elementData[++elementCount] = item;
        }
    }

    public Object[] grow(){
        //这里为了简便先不考虑扩容时候的数组越界问题
        return elementData = Arrays.copyOf(elementData,elementCount*2);
    }

    //出栈
    public T pop(){
        if (isEmpty())
            throw new EmptyStackException();
        T item = peek();
        elementCount--;
        elementData[elementCount] = null;
        return item;
    }

    //获取战队元素,但不移除
    public T peek(){
        if (isEmpty())
            throw new EmptyStackException();
        return (T) elementData[elementCount-1];
    }


    public boolean isFull(){
        return top == elementData.length;
    }

    public boolean isEmpty(){
        return top == -1;
    }


    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(15);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
