package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，
 * 如果两个链表相交，请返回相交的第一个节点。
 * 如果不相交，返回null
 * 【要求】 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度达到O(1)。
 * <p>
 * 作者：waigo
 * 链接：https://www.jianshu.com/p/44a18aadcd59
 * <p>
 * 思路：两个链表可能有环，也可能无环，所以先判断两个链表是否有环：
 * 1.如果两个都没有环，就是普通的两链表找公共节点
 * 2.如果一个有环，一个无环，则不存在交点
 * 3.如果都有环，则判断环的入口点是不是同一个，可能是同一个，也可能不是同一个：
 * (1)如果是一个，则交点在环以外的公共链表部分，也即1中存在公共节点的情况；
 * (2)如果不是一个，则任意选一个入口点沿着环遍历，看能否找到另一个入口点，
 * 找到的话随便返回一个入口点即可，否则无交点
 */
public class 两个可能有环链表可能相交求交点问题_困难 {

    public static ListNode getIntersectNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        //找到入环节点
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);

        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 判断是否有环，并返回入环节点 @ref{链表中环的入口节点.EntryNodeOfLoop}
     */
    public static ListNode getLoopNode(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast.next == null || fast.next.next == null) {
            return null;
        }
        slow = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 两个无环链表的公共节点
     */
    public static ListNode noLoop(ListNode head1, ListNode head2) {
        ListNode cur1 = head1;
        ListNode cur2 = head2;

        while (cur1 != cur2) {
            cur1 = cur1 != null ? cur1.next : head2;
            cur2 = cur2 != null ? cur2.next : head1;
        }
        return cur1;
    }

    /**
     * 两个有环链表的公共节点
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static ListNode bothLoop(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        if (loop1 == loop2) {  //断开环，复用无环链表的处理
            loop1.next = null;
            return noLoop(head1, head2);
        }
        ListNode cur1 = loop1.next;
        while (cur1 != loop1) {  //在环上找一圈
            if (cur1 == loop2) {
                return loop1;
            }
            cur1 = cur1.next;
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        // 0->9->8->6->7->null
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->6->4->5->6..
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next; // 8->5
        System.out.println(getIntersectNode(head1, head2).val);
    }
}
