package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.为难人系列;

public class 循环队列实现 {
    //数组实现循环队列
    static class CircularQueue {
        private Integer[] elementData;
        private int front;  //队首
        private int rear;  //队尾
        private int size;  //元素个数
        private int capacity;  //数组容量

        public CircularQueue(int capacity) {
            if (capacity <= 0) throw new RuntimeException("capacity must be at least 1");
            this.capacity = capacity;
            elementData = new Integer[capacity];
        }

        public boolean offer(int element) {
            //第一种方法：校验队列是否已满, 如果队列已满则不新增  空一格
//            if ((rear + 1) % capacity == front) {
//                System.out.println("队列已满, 不可再新增数据");
//                return false;
//            }
            //第二种方法：通过rear == front 和size来控制
            if (rear == front && size == capacity) {
                System.out.println("队列已满, 不可再新增数据");
                return false;
            }
            elementData[rear] = element;
            size++;
            // 将队尾重新赋值，这里不直接进行加一的原因在于可能会出现假溢出的情况
            // 比如说已经存在队尾的索引为4,（4 + 1） % 5 = 0 => 队尾指向了索引下标为0
            rear = (rear + 1) % capacity;
            return true;
        }

        /**
         * 出队，数据从队首中被移除
         * @return
         */
        public Integer poll() {
            // 校验队列是否为空，为空则不允许删除
            if (rear == front && size == 0) {
                System.out.println("队列为空，不允许删除");
                return null;
            }
            Integer elementDatum = elementData[front];
            elementData[front] = null;
            // 将队首重新赋值，赋值逻辑和队尾逻辑类似
            front = (front + 1) % capacity;
            // 将队列数据长度进行减一操作
            size--;
            return elementDatum;
        }
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        queue.offer(21);
        queue.offer(22);
        queue.offer(23);
        queue.offer(24);
        queue.offer(25);
        queue.offer(26);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.front);
        System.out.println(queue.rear);


        LinkedCircularQueue<Integer> queue1 = new LinkedCircularQueue<>(5);
        queue1.offer(21);
        queue1.offer(22);
        queue1.offer(23);
        queue1.offer(24);
        queue1.offer(25);
        queue1.offer(26);
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.size);
    }


    static class LinkedCircularQueue<E> {
        class Node<E> {
            private E element;
            private Node next;  //单向
            public Node(E element) {
                this.element = element;
            }
        }

        private Node<E> tail;
        private int size;
        private int capacity;

        public LinkedCircularQueue(int capacity) {
            if (capacity < 1) throw new RuntimeException("capacity must be at least 1");
            this.capacity = capacity;
            this.size = 0;
            tail = null;
        }

        /**
         * 入队
         * @param element
         */
        public boolean offer(E element) {
            Node<E> eNode = new Node<>(element);
            if (size == capacity) {  //队列已满
                System.out.println("队列已满, 不可再新增数据");
                return false;
            } else if (tail == null) {  //队列为空
                tail = eNode;
                tail.next = tail;
            } else {   //队列有元素
                eNode.next = tail.next;
                tail = tail.next = eNode;
            }
            size++;
            return true;
        }

        /**
         * 出队
         * @return
         */
        public E poll() {
            Node<E> rmNode = null;
            if (tail == null) {
                System.out.println("队列为空，无法进行删除操作");
                return null;
            }else if(tail.next == tail){ //只有一个元素
                rmNode = tail;
                tail = null;
            }else{
                rmNode = tail.next;
                tail.next = rmNode.next;
            }
            size--;
            rmNode.next = null;
            return rmNode.element;
        }
    }
}
