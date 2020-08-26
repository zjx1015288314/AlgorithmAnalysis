package com.zjx.DataStructure.Chapter3;

public class ArrayTwoStack<T> {
    /**
     * 用数组实现两个栈,两个栈一个从数组头部开始,一个从数组尾部开始,直至相遇
     */
    private Object[] elementData;
    private int elementCount;  //数组元素个数
    private int top1;   //游标1
    private int top2;   //游标2

    public ArrayTwoStack(int capacity) {
        elementData = new Object[capacity];
        top1 = -1;
        top2 = capacity;
    }


    //定义两个栈的push和pop
    public boolean push1(T item) {
        if (top1 + 1 == top2)
            return false;
        else {
            elementData[++top1] = item;
            elementCount++;
            return true;
        }
    }

    public boolean push2(T item) {
        if (top2 - 1 == top1)
            return false;
        else {
            elementData[--top2] = item;
            elementCount++;
            return true;
        }
    }

    public T pop1() {
        if (top1 < 0)
            return null;
        else {
            T item = (T) elementData[top1];
            elementData[top1--] = null;
            elementCount--;
            return item;
        }
    }

    public T pop2() {
        if (top2 >= elementData.length)
            return null;
        else {
            T item = (T) elementData[top2];
            elementData[top2++] = null;
            elementCount--;
            return item;
        }
    }

    //返回数组元素个数
    public int size() {
        return elementCount;
    }

    //打印堆栈的元素
    public void print_stack1() {
        if (top1 == -1) {
            System.out.println("stack1 is empty!!!");
        } else {
            for (int i = 0; i <= top1; i++) {
                System.out.print(elementData[i] + " ");
            }
            System.out.println();
        }
    }

    public void print_stack2() {
        int len = elementData.length;
        if (top2 == len) {
            System.out.println("stack1 is empty!!!");
        } else {
            for (int i = len - 1; i >= top2; i--) {
                System.out.print(elementData[i] + " ");
            }
            System.out.println();
        }
    }

}
