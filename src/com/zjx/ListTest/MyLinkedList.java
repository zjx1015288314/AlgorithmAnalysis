package com.zjx.ListTest;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

    private int theSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    private static class Node<T>{
        /*3.25*/
        public T data;
        public Node<T> prev;
        public Node<T> next;
        public Node(T d,Node<T> p,Node<T> n){
            data = d;
            prev = p;
            next = n;
        }
    }
    public MyLinkedList(){
        doClear();
    }

    public void doClear() {
        /*3.26*/
        beginMarker = new Node<>(null,null,null);
        beginMarker = new Node<>(null,beginMarker,null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }
    public boolean add(T x){
        add(size(),x);
        return true;
    }

    public void add(int idx, T x) {
        addBefore(getNode(idx,0,size()),x);
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    public T set(Node<T> p,T newVal){
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }
    public T set(int idx,T newVal){
        Node<T> p = getNode(idx);
        return set(p,newVal);
    }

    public T remove(int idx){
        return remove(getNode(idx));
    }

    private void addBefore(Node<T> p,T t){
        /*3.28*/
        Node<T>  newNode = new Node<>(t,p.prev,p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }
    private T remove(Node<T> p){
        /*3.30*/
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    private Node<T> getNode(int idx){
        /*3.31*/
        return getNode(idx,0,size()-1);
    }

    private Node<T> getNode(int idx,int lower,int upper){
        /*3.31*/
        Node<T> p;
        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        if (idx < size()/2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        }else{
            p = endMarker;
            for (int i = size(); i > idx ; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    public ListIterator<T> listIterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements ListIterator<T>{
        /*3/32*/
        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        @Override
        public boolean hasNext() {
            return current != endMarker;  //inkedList用nextIndex当做游标,而在这里则用当前节点当做标志
        }

        @Override
        public T next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            T nextItem = current.data;
            current = current.next;
            okToRemove = true;    //LinkedList中是用lastReturn记录了已返回的最后一个节点,
            // 这里只记录下一次要遍历的节点,所以在remove()中要传current.prev
            return nextItem;
        }

        @Override
        public boolean hasPrevious() {
            return current.prev != beginMarker ;
        }

        @Override
        public T previous() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            current = current.prev;
            T previousItem = current.data;
            okToRemove = true;
            return previousItem;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }

        @Override
        public void set(T newVal) {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            MyLinkedList.this.set(current.prev,newVal);
        }

        @Override
        public void add(T t) {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            addBefore(current.next,t);      //这里不懂？？为什么不是current
                                            //LinkedList中remove，add，set若一起调用则顺序是setremoveaddaddadd,
                                            //具体的用法可以参考LinkedList
        }

    }

    public void addFirst(T t){
        addBefore(beginMarker.next,t);
    }
    public void addLast(T t){
        addBefore(endMarker,t);
    }
    public void removeFirst(T t){
        remove(beginMarker.next);
    }
    public void removeLast(T t){
        remove(endMarker);
    }
    public T getFirst(T t){
        return get(0);
    }
    public T getLast(T t){
        return get(size()-1);
    }
}
