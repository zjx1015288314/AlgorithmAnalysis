package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

/**
 * 将一个链表 m 位置到 n位置之间的区间反转，要求时间复杂度 O(N)，空间复杂度 O(1)。
 * 例如：
 * 给出的链表为 1→2→3→4→5→NULL, 返回 1→4→3→2→5→NULL.
 * 注意：
 * 给出的 满足以下条件：
 * 1≤m≤n≤链表长度
 */
public class 指定区间反转链表 {
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        // write code here
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int len = 0;

        ListNode fPre = dummy;
        ListNode tPos = null;
        ListNode cur = head;

        while (cur != null) {
            len++;
            fPre = len == m - 1 ? cur : fPre;
            tPos = len == n + 1 ? cur : tPos;
            cur = cur.next;
        }
        if (m > n || m < 1 || n > len) {
            return head;
        }
        reverse(fPre, tPos);
        return dummy.next;
    }

    public void reverse(ListNode fPre, ListNode tPos) {
        ListNode pre = tPos;
        ListNode start = fPre.next;
        ListNode next = null;
        while (start != tPos) {
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        fPre.next = pre;
    }

    /**
     * 不带dummyNode的写法
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        int len = 0;
        ListNode pre = null;
        ListNode cur = head;
        ListNode post = null;

        while(cur != null) {
            len++;
            pre = len == left - 1 ? cur : pre;
            post = len == right + 1 ? cur : post;
            cur = cur.next;
        }
        if(left > right || left < 1 || right > len) {
            return null;
        }
        return reverse(head, pre, post);
    }

    public ListNode reverse(ListNode head, ListNode fPre, ListNode tPos) {
        ListNode cur = fPre == null ? head : fPre.next;
        ListNode pre = tPos;

        while(cur != tPos) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if(fPre != null) {
            fPre.next = pre;
            return head;
        } else {
            return pre;
        }
    }
}
