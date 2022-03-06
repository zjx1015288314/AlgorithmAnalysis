package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import java.util.HashMap;
import java.util.Map;

/**
 *  输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 *  另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷
 *  贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程
 *  序会直接返回空）。 下图是一个含有5个结点的复杂链表。图中实线箭头表示next指针，
 *  虚线箭头表示random指针。为简单起见，指向null的指针没有画出。
 * @link https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 复杂链表复制 {

    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead) {
        Map<RandomListNode, RandomListNode> randomListNodeToListNode = new HashMap<>();

        RandomListNode cur = pHead;
        RandomListNode dummyHead = new RandomListNode(-1);
        RandomListNode cloneHead = dummyHead;

        while(cur != null){
            cloneHead = cloneHead.next = new RandomListNode(cur.label);
            randomListNodeToListNode.put(cur, cloneHead);
            cur = cur.next;
        }

        cur = pHead;
        while(cur != null){
            RandomListNode cloneSrcNode = randomListNodeToListNode.get(cur);
            RandomListNode cloneDestNode = randomListNodeToListNode.get(cur.random);
            cloneSrcNode.random = cloneDestNode;
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
