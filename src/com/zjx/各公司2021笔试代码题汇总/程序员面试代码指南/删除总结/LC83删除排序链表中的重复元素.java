package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;

/**
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class LC83删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            pre.next = null;   //写在这里也可
            //前后不一致时pre紧跟着cur
            if (cur.val != pre.val) {
                pre = pre.next = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public ListNode deleteDuplicates1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            // cur到达尾部或者cur与cur.next不相等时，pre.next = cur
            if (cur.next == null || cur.val != cur.next.val) {
                pre = pre.next = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}


