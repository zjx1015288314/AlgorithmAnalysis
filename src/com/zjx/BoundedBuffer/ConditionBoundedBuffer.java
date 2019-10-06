package com.zjx.BoundedBuffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {
    private static int BUFFER_SIZE = 16;
    protected final Lock lock = new ReentrantLock();
    //条件谓词：notFull(Count < items.length)
    private final Condition notFull = lock.newCondition();
    //条件谓词：notEmpty(Count > 0)
    private final Condition notEmpty = lock.newCondition();
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    private int head,tail,count;


    //阻塞直到notFull
    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[tail++] = t;
            if (tail == items.length)
                tail = 0;
            count++;
            notEmpty.signal();  //signal比signalAll更高效,它极大地减少在每次缓存操作中发生的上下文切换，以及锁清秋的次数
        }finally {
            lock.unlock();
        }

    }

    //阻塞直到notEmpty
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            T t = items[head++  ];
            if (head == items.length)
                head = 0;
            count--;
            notFull.signal();
            return t;
        }finally {
            lock.unlock();
        }

    }
}
