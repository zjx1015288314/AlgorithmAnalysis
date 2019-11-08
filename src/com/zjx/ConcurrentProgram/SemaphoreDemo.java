package com.zjx.ConcurrentProgram;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

//消费者生产者模型
public class SemaphoreDemo {
    private Semaphore produceSem;
    private Semaphore customerSem;

    private Semaphore mutex;
    private Object[] warehouse;
    private int head,tail;
    public SemaphoreDemo(int capacity){
        produceSem = new Semaphore(capacity);
        customerSem = new Semaphore(0);   //其实设置为0后是可以release的,然后就可以acquire.
                                                  // 这里设置为0，就是一开始使线程阻塞从而完成其他执行
        warehouse = new Object[capacity];
        head = 0;
        tail = 0;
        mutex = new Semaphore(1);
    }

    public void put(Object o){
        try {
            produceSem.acquire();//获取存储资格
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        putObject(o);

        customerSem.release();//有消费的资源了
    }

    private void putObject(Object obj){
        try {
            //锁定
            mutex.acquire();
            warehouse[tail++] = obj;
            if(tail==warehouse.length){
                tail = 0;
            }
            System.out.println(Thread.currentThread().getName()+"生产产品：   "+(Integer)obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            //释放锁
            mutex.release();
        }

    }

    public Object get(){
        try {
            customerSem.acquire();//保证有资源可以消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object obj = getObject();
        //System.out.println(Thread.currentThread().getName()+"拿到产品:  "+obj);
        produceSem.release();// 增加可以生产的信号量
        return obj;
    }

    private Object getObject() {
        try {
            mutex.acquire();//类似于获取锁
            Object obj = warehouse[head];

            head++;
            if(head==warehouse.length){
                head = 0;
            }
            System.out.println(Thread.currentThread().getName()+"拿到产品:  "+obj);
            return obj;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally{
            mutex.release();
        }

        return null;
    }
    private static AtomicInteger at = new AtomicInteger(0);
    public static void main(String[] args){
        SemaphoreDemo sd = new SemaphoreDemo(10);
        //开启3个生产者、消费者线程
        for(int i=0;i<3;i++){
            new Thread(new Runnable(){

                @Override
                public void run() {
                    while(true){
                        int val = at.incrementAndGet();
                        sd.put(val);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            },"produceThread"+i).start();
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while(true){
                        sd.get();
                        //System.out.println(Thread.currentThread().getName()+"拿到的产品为:"+str);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            },"customerThread"+i).start();
        }
    }

}
