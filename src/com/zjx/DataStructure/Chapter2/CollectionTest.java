package com.zjx.DataStructure.Chapter2;

import java.util.Arrays;

public class CollectionTest<E> {
    private Object[] objects;
    private final int DEFAULT_CAPACITY = 16;
    //当数组为空的时候，定义数组
    private static final Object[] EMPTY_ELEMENTDATA = {};
    //定义数组的最大size=Integer.MAX_VALUE - 8
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private int capacity;
    private int size;

    public CollectionTest(Object[] objects){
        this.objects = objects;

    }
    public CollectionTest(){
        objects = EMPTY_ELEMENTDATA;
    }

    public boolean isEmpty(){
        return size > 0;
    }

    public void makeEmpty(){
        for (int i = 0; i < size; i++)
            objects[i] = null;
        size = 0;
    }

    public boolean insert(E obj){

        try {
            checkArea(size+1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            objects[size++] = obj ;
            return true;
        }
    }

    public void checkArea(int minCapacity) throws Exception {
        if (minCapacity < 0){
            throw new Exception("array overflow");
        }
        if(objects ==EMPTY_ELEMENTDATA){
            minCapacity=Math.max(minCapacity,DEFAULT_CAPACITY);
        }

        if(minCapacity-size<0){
            //如果最小需要大于数组现有大小，调用数组扩容
            grow(minCapacity);

        }

    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = objects.length;

        //新的数组容量=老的数组长度的1.5倍。oldCapacity >> 1 相当于除以2
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        //如果新的数组容量newCapacity小于传入的参数要求的最小容量minCapacity，那么新的数组容量以传入的容量参数为准。
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        //如果新的数组容量newCapacity大于数组能容纳的最大元素个数 MAX_ARRAY_SIZE 2^{31}-1-8
        //调用hugeCapacity方法
        if (newCapacity - MAX_ARRAY_SIZE > 0)

            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        objects = Arrays.copyOf(objects, newCapacity);
    }

    //hugeCapacity再判断传入的参数minCapacity是否大于MAX_ARRAY_SIZE，
    //如果minCapacity大于MAX_ARRAY_SIZE，那么
    //newCapacity等于Integer.MAX_VALUE，否则newCapacity等于MAX_ARRAY_SIZE

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (objects[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(objects[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(objects, index+1, objects, index,
                    numMoved);     //很巧妙
        objects[--size] = null; // clear to let GC do its work
    }

    public boolean isPresent(E x) {
        boolean isPre = false;
        for (int i = 0; i <= size - 1; i++) {
            if (objects.equals(x)) {
                isPre = true;
            }
        }
        return isPre;
    }

}
