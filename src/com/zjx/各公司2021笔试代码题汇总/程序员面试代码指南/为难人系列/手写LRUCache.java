package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.为难人系列;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class 手写LRUCache {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] numStrArr = in.readLine().split(" ");
        int K = Integer.valueOf(numStrArr[0]);
        LRUCache<Integer, Integer> cache = new LRUCache<>(K);    // 创建容量为K的LRU缓存
        String ss = null;
        while((ss = in.readLine()) != null){
            numStrArr = ss.split(" ");
            switch(numStrArr[0]){
                case "p":
                    cache.put(Integer.valueOf(numStrArr[1]),Integer.valueOf(numStrArr[2]));
                    break;
                case "g":
                    Integer value = cache.get(Integer.valueOf(numStrArr[1]));
                    System.out.print((value == null ? -1 : value) + "\n");
                    break;
            }
        }
//        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<Integer, Integer>(K,0.75f,true){
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//                return size() > K;
//            }
//        };
//        String str = null;
//        while((str = in.readLine()) != null){
//            String[] ss = str.split(" ");
//            switch(ss[0]){
//                case "p":
//                    map.put(Integer.valueOf(ss[1]),Integer.valueOf(ss[2]));
//                    break;
//                case "g":
//                    Integer value = map.get(Integer.valueOf(ss[1]));
//                    System.out.print((value == null ? -1 : value) + "\n");
//                    break;
//            }
//        }
    }
}

// 双端队列 + 哈希表 实现LRU缓存结构
class LRUCache<K, V> {
    DoubleLinkedList<V> dList;
    Map<K, Node<V>> keyNodeMap;    // <key, node>
    Map<Node<V>, K> nodeKeyMap;    // <node, key>   删除最旧节点时需要用到, 如果Node节点有key，则不需要该map
    int capacity;                  // 缓存容量

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new RuntimeException("the capacity is at least 1");
        }
        this.dList = new DoubleLinkedList<>();
        this.keyNodeMap = new HashMap<>();
        this.nodeKeyMap = new HashMap<>();
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        if (keyNodeMap.containsKey(key)) {
            Node<V> node = keyNodeMap.get(key);
            node.value = value;
            dList.moveNodeToTail(node);
        } else {
            Node<V> newNode = new Node<>(value);
            keyNodeMap.put(key, newNode);
            nodeKeyMap.put(newNode, key);
            dList.addNodeToTail(newNode);
            if (keyNodeMap.size() > this.capacity) {    // 超出容量，移除最久未使用的节点
                Node<V> rmNode = dList.removeHead();
                K rmKey = nodeKeyMap.get(rmNode);
                keyNodeMap.remove(rmKey);
                nodeKeyMap.remove(rmNode);
            }
        }
    }

    public V get(K key) {
        if (keyNodeMap.containsKey(key)) {
            Node<V> node = keyNodeMap.get(key);
            dList.moveNodeToTail(node);
            return node.value;
        }
        return null;
    }
}



// 双端链表，头部优先级最低，尾部优先级最高
class DoubleLinkedList<V> {
    Node<V> head;
    Node<V> tail;

    public DoubleLinkedList() {
        head = null;
        tail = null;
    }

    public void addNodeToTail(Node<V> node) {
        if (node == null) {
            return;
        }
        if (head == null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail = tail.next = node;
        }
    }

    public void moveNodeToTail(Node<V> node) {
        if (node == null || node == tail) {
            return;
        }
        //该节点是否头结点,先调整node前后节点的连接，再调整node节点连接
        if (node == head) {
            head = head.next;
            head.prev = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node.prev = tail;
        node.next = null;
        tail = tail.next = node;
    }

    public Node<V> removeHead() {
        //一般情况下是不会出现出现这种情况
        if (head == null) {
            return null;
        }
        Node<V> rmNode = head;
        //一般情况下是不会出现出现这种情况，因为设置了容量>=1,但这里方法为public，考虑最坏情况
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            rmNode.next = null;
            head.prev = null;
        }
        return rmNode;
    }
}

class Node<V> {
    V value;
    Node<V> next;
    Node<V> prev;
    public Node(V value) {
        this.value = value;
    }
}