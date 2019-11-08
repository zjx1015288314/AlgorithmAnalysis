package com.zjx.ListTest;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] theItems;
    private int modCount = 0;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public T get(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public T set(int idx, T newVal) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    //使数组扩容
    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;
        T[] old = theItems;     //不可初始化泛型数组，即：T[] old = new T[];
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, T x) {
        modCount++;
        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int i = theSize; i > idx; i--)
            theItems[i] = theItems[i - 1];    //ArrayList中使用了System.arrayCopy(elementData,idx,elementData,idx+1,size-idx)
        theItems[idx] = x;
    }

    public T remove(int idx) {
        modCount++;
        T removedItem = theItems[idx];
        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removedItem;

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    public ListIterator listIterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements ListIterator<T> {
        //ArrayList中Itr实现了Iterator,并设置了两个属性cursor和lastRet,cursor表示下一个要返回的元素的索引
        //lastRet表示已返回的最后一个元素的索引,且每次迭代只能在调用一次next的前提下只能调用一次remove，否则会抛异常
        //与java.util.ArrayList中iterator相比,少了lastRet属性(即不限制每次迭代时只能使用一次add()/remove())
        private int current = 0;
        boolean backwords = false;  //因为ListIterator是双向迭代,所以增加了一个属性来判断是否是由尾向头迭代,默认从头开始
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return current < size();    //ArrayList中是cursor != size
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();
            backwords = false;
            return theItems[current++];     //这里“current++”是返回游标指向的元素,符合next,因为current始终等于元素索引
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public T previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();
            backwords = true;
            return theItems[--current];   //这里“--current”的意思是返回游标之前的元素,符合previous,因为current始终比
                                          //对于的元素索引大1
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
            checkForComodification();
            if (backwords)
                MyArrayList.this.remove(current--);  //当外部类与内部类有重名的方法时,想要在内部类中使用外部类方法,
                                                    //就要用XXX.this.XXX()
            else
                MyArrayList.this.remove(--current);   //正向移除
            expectedModCount = modCount;
        }

        @Override
        public void set(T newVal) {
            checkForComodification();
            MyArrayList.this.set(current,newVal);
        }

        @Override
        public void add(T t) {
            checkForComodification();
            MyArrayList.this.add(current++,t);   //在当前位置添加元素,游标后移(不管当前是正向还是反向)
            expectedModCount = modCount;
        }

        //next(),previous(),remove(),add(),set()都要加上该检测方法
        //关于fast-fail机制,ArrayList与LinkedLIst的add/remove并不一样,ArrayList中Iterator因为调用了
        //外部类的方法(外部类方法中有modCount++),所以这里是重新赋值;
        //而LinkedList中是直接expectedModCount++,并在forEachRemaining中将expectedModCount赋值给count
        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private Iterator<T> reverseIterator(){ return new ArrayListReverseIterator();}

    private class ArrayListReverseIterator implements Iterator<T>{

        private int current = size() - 1;

        @Override
        public boolean hasNext() {
            return current >= 0;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return theItems[current--];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(++current);   //答案是--current,但感觉这里是++current
        }
    }

}
