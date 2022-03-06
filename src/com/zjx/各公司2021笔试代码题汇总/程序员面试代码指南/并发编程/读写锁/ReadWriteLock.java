package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并发编程.读写锁;

public class ReadWriteLock {
    private int readingReaders = 0;
    private int waitingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;
    private boolean preferWriter = true;     //防止写线程饥饿

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    public synchronized void readLock() throws InterruptedException {
        try {
            this.waitingReaders++;
            while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnLock() {
        this.readingReaders--;
        this.notifyAll();  //唤醒所有
    }

    public synchronized void writeLock() throws InterruptedException {
        try {
            this.waitingWriters++;
            while (writingWriters > 0 || readingReaders > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnLock() {
        this.writingWriters--;
        this.notifyAll();
    }
}
