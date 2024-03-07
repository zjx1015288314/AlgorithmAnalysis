package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.为难人系列;


import java.util.HashMap;
import java.util.Map;

/**
 * 一个缓存结构需要实现如下功能。
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * 但是缓存结构中最多放K条记录，如果新的第K+1条记录要加入，就需要根据策略删掉一条记录，然后才能把新记录加入。
 * 这个策略为：在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录；
 * 如果调用次数最少的key有多个，上次调用发生最早的key被删除
 * 这就是LFU缓存替换算法。实现这个结构，K作为参数给出
 * [要求]
 * set和get方法的时间复杂度为O(1)
 *
 * 若opt=1，接下来两个 整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 *
 * 对于每个操作2，输出一个答案
 */
public class 手写LFU {
    public int[] LFU (int[][] operators, int k) {
        // write code here
        LFUCache cache = new LFUCache(k);
        int[] res = new int[operators.length];
        int idx = 0;
        for(int i = 0; i < operators.length; i++){
            if(operators[i][0] == 1){
                cache.set(operators[i][1],operators[i][2]);
            }else{
                Integer num = cache.get(operators[i][1]);
                res[idx++] = num == null ? -1 : num;
            }
        }
        return res;
    }
}
class LFUCache{
    int capacity;
    int size;
    int minFrequency;
    Map<Integer,ListNode> res; //key为节点的key，value为节点
    Map<Integer,DLinkedList> frequencyMap;  //key为频率，value为对应的链表

    public LFUCache(int capacity){
        if(capacity < 1){
            throw new RuntimeException("capacity must not be less than 1");
        }
        this.capacity = capacity;
        minFrequency = 1;
        res = new HashMap<>();
        frequencyMap = new HashMap<>();
    }


    public Integer get(Integer key){
        if(res.containsKey(key)){
            ListNode node = res.get(key);
            freIcr(node);
            return node.value;
        }
        return null;
    }

    public void set(Integer key,Integer value){
        //题目中K>=1,且我们已经在初始化时作了限制，所以这里不用判断capacity为0，如果题目没有明确表明K，则需要
        //if(capacity == 0) return;
        if(res.containsKey(key)){
            ListNode node = res.get(key);
            node.value = value; //覆盖
            freIcr(node);
        }else{
            //与LRU不同，先判断再插入，因为先把值插入的话，如果此时该节点频率就是最小的且只有一个，那么后面马上会删除该节点
            if(size == capacity){
                DLinkedList list = frequencyMap.get(minFrequency);
                ListNode deleteNode = list.head.next;
                list.deleteNode(deleteNode);
                res.remove(deleteNode.key);
                size--;
                //我们对于为空的列表不做删除
                //if(list.head == list.tail){
                //frequencyMap.remove(minFrequency);
                //}
            }
            ListNode node = new ListNode(key,value);
            res.put(key,node);
            DLinkedList list = frequencyMap.get(node.frequency);
            if(list == null){
                list = new DLinkedList();
                frequencyMap.put(node.frequency,list);
            }
            list.addNodeToTail(node);
            minFrequency = node.frequency;  //每次新增都会变
            size++;
        }
    }

    //node存在于res中的前提下
    public void freIcr(ListNode node){
        if(node == null) return;
        //这里我们设定node对应的DLinkedList必定存在，因为freIcr都是在key存在的情况下进入的
        DLinkedList list = frequencyMap.get(node.frequency);
        list.deleteNode(node);  //频率变更
        if(list.head == list.tail && minFrequency == node.frequency){
            //frequencyMap.remove(node.frequency);   //为什么这里不删除旧的？
            minFrequency = node.frequency + 1;
        }
        //修改node的频率,并加入新的链表中
        int fre = ++node.frequency;
        DLinkedList newList = frequencyMap.get(fre);
        if(newList == null){ //不存在
            newList = new DLinkedList();
            frequencyMap.put(fre,newList);
        }
        newList.addNodeToTail(node);
    }
}
class DLinkedList{
    ListNode head;
    ListNode tail;

    //头尾节点设置为虚节点，这样在增删节点时，不必进行繁琐的null判断
    public DLinkedList(){
        head = new ListNode(null,null);
        tail = new ListNode(null,null);
        head.next = tail;
        tail.prev = head;
    }

    //在节点频率变更或者容量达到最大时使用，另当容量最大时需考虑head节点
    public void deleteNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
    }

    public void addNodeToTail(ListNode node){
        ListNode pre = tail.prev;
        pre.next = node;
        node.prev = pre;
        node.next = tail;
        tail.prev = node;
    }
}
class ListNode{
    Integer key; //冗余值，为了在容量达到最大值时删除frequencyMap中某个节点时能删除res中对应的node节点
    Integer value;
    int frequency; //冗余值，在频率变化的时候能及时更新
    ListNode next;
    ListNode prev;
    public ListNode(Integer key,Integer value){
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }
}
