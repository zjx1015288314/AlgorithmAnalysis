package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;


/**
 * 输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
 * 如果该链表长度小于k，请返回一个长度为 0 的链表。
 * 要求：空间复杂度O(n)，时间复杂度O(n)
 * 进阶：空间复杂度O(1)，时间复杂度O(n)
 *
 * 思路：找到正数第k个节点，该节点距离链表开始的长度为是k。
 * 同时让一个指针从链表开始走，第二个指针从第k个节点开始走。当第二个指针走到链表末尾时，第一个指针就走到了倒数第k个节点。
 * 注意：链表为空，k<=0，k>链表长度的情况！！！
 * https://www.nowcoder.com/practice/886370fe658f41b498d40fb34ae76ff9?tpId=295&tqId=1377477&ru=/exam/company&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Fcompany
 */
public class 链表倒数第k个节点 {
    public ListNode findKthToTail (ListNode pHead, int k) {
        // write code here
        if(pHead == null) return null;
        ListNode first = pHead;
        ListNode second = pHead;
        int idx = 0;
        while(idx < k && first != null) {
            first = first.next;
            idx++;
        }

        while(first != null) {
            second = second.next;
            first = first.next;
        }

        //链表长度小于k
        if(idx < k) return null;
        return second;
    }
}
