package com.zjx.DataStructure.Chapter3;

public class SingleLinkedListQueue<T> {
    /**
     * 3.32 使用单链表来实现队列
     */
    private Node<T> front,rear;

    SingleLinkedListQueue(){
        front = rear = null;
    }

    private class Node<T>{
        private T data;
        private Node<T> next;

        Node(){
            this(null,null);
        }
        Node(T data){
            this(data,null);
        }
        Node(T data,Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

    public void enqueue(T item){
        Node<T> node = new Node<>(item);
        //这个判断很经典,if分支既将尾结点的next链接node,也移动rear指向node;else分支就是链表为空时,头尾节点指向同一node节点
        if (rear != null)
            rear = rear.next = node;
        else
            front = rear = node;
    }

    public T dequeue() throws Exception {
        if (front == null)
            throw new Exception("队列为空,元素不可出栈!!");
        T item = front.data;

        if (front.next == null)
            front = rear = null;
        else {
            Node<T> node = front;
            front = front.next;
            node.next = null;
            node.data = null;  //help GC,防止内存泄漏
        }
        return item;
    }
}
