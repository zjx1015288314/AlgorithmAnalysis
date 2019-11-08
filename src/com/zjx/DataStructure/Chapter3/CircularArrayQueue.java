package com.zjx.DataStructure.Chapter3;

import java.util.ArrayList;

/**
 * front和rear一共会有三种状态:1)两者相等 2)rear超过front 3)front超过rear
 * 1)表示该队列为空  2)和3)大部分表示队列非空,只有当(rear+1)%maxSize == front时,队列为满,这样做是为了考虑循环队列
 * 在满的情况下还可以填入元素,符合语义
 * @param <T>
 */

public class CircularArrayQueue<T> {
    private int maxSize;
    private int front,rear;
    private ArrayList<T> elements;
    CircularArrayQueue(){
        this(10);
    }
    CircularArrayQueue(int capacity){
        maxSize = capacity;
        front = rear = 0;
        elements = new ArrayList<>(maxSize);
    }

    public void enqueue(T item){
        if (!full()){
            //有两种情况：1)是真的未满,此时直接添加即可  2)rear已经从后面超过front,为了语义,这里也规定为未满
            if (elements.size() < maxSize)
                elements.add(item);   //直接在末尾添加
            else
                elements.set(rear,item);  //替换位置上的旧值
            rear = (rear + 1) % maxSize;
        }
    }

    //该方法直接忽略了满队的情况
    public T dequeue(){
        T tmp = null;
        if (!empty()) {
            tmp = elements.get(front);
            front =  (front + 1) % maxSize;
        }
        return tmp;
    }


    private boolean full(){
        return (rear + 1) % maxSize == front;
    }

    private boolean empty(){
        return rear == front;
    }
}
