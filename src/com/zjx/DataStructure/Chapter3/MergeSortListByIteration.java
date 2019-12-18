package com.zjx.DataStructure.Chapter3;

/**
 * 在 O(nlogn)时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 */
public class MergeSortListByIteration {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 方法一：题目要求是常数级空间复杂度，所以递归不适合，因为递归会造成栈的深度为O(logN),所以改为迭代
     * 思路: 设n表示需要归并的长度. n从1,2,3,…开始.
     * 第1轮归并, 把连接切割为长度n=1个节点的小链表; 两个小链表归并;归并好的按顺序连接在一起.
     * 例如: 4->2->1->3->6->5->9->7
     * 4和2归并为2->4;
     * 1和3归并为1->3;
     * 6和5归并为5->6;
     * 9和7归并为7->9;
     * 第1轮归并后的结果为:2->4->1->3->5->6->7->9
     * 第2轮归并, 把连接切割为长度n=2个节点的小链表; 两个小链表归并;归并好的按顺序连接在一起.
     * 2->4与1->3归并为1->2->3->4;
     * 5->6与7->9归并为5->6->7->9;
     * 第2轮归并后的结果为: 1->2->3->4 -> 5->6->7->9
     * 第3轮归并, 把连接切割为长度n=4个节点的小链表; 两个小链表归并;归并好的按顺序连接在一起.
     * 1->2->3->4 与 5->6->7->9 归并为 1->2->3->4 -> 5->6->7->9
     * 第3轮归并后的结果为: 1->2->3->4 -> 5->6->7->9
     * 第4轮归并, 把连接切割为长度n=8个节点的小链表; 两个小链表归并;归并好的按顺序连接在一起.
     * 一个链表是 1->2->3->4 -> 5->6->7->9 ,另一个是空链表, 说明归并完成
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //统计链表长度
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        head = dummy.next;
        //共循环logn次，每次循环都将进行一次全链表的遍历并逐步恢复次序
        for (int i = 1; i < length; i += i) {
            //将链表分为四段，第一段为已排序的部分,第二段为待排序的第一部分(由first表示头节点),第三段为待排序的第二部分(由second表示头节点),第四段为未排序的部分(由head节点表示头节点)
            ListNode succ = dummy;
            ListNode first = null;
            ListNode second = null;
            while (head != null) {
                first = head;
                head = cutFromHead(head, i);
                second = head;
                head = cutFromHead(head, i);
                //将两段链接到succ尾部,并移动succ
                succ.next = mergeLists(first, second);
                while (succ.next != null) succ = succ.next;
            }
            head = dummy.next;
        }
        return dummy.next;

    }

    //将链表从head节点开始的n个节点切断，并返回第n+1个节点
    public ListNode cutFromHead(ListNode head, int n) {
        while (head != null && --n > 0) {
            head = head.next;
        }
        if (head == null) return null;
        ListNode next = head.next;
        head.next = null;
        return next;
    }

    //将两个链表合并
    public ListNode mergeLists(ListNode first, ListNode second) {
        if (first == null) return second;
        if (second == null) return first;
        ListNode dummy = new ListNode(-1);
        ListNode succ = dummy;
        while (first != null && second != null) {
            if (first.val < second.val) {
                succ.next = first;
                first = first.next;
            } else {
                succ.next = second;
                second = second.next;
            }
            succ = succ.next;
        }
        succ.next = first != null ? first : second;
        return dummy.next;
    }


    /**
     * 为了练习递归写法，也可以试着写一写递归版归并排序以及快排
     */

    //do Something
}
