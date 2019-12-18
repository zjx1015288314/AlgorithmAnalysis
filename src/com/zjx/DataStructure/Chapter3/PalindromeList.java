package com.zjx.DataStructure.Chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 */
public class PalindromeList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 方法一：投机取巧，使用jdk自带的ArrayList完成头尾查询功能,开始先将所有的节点加入list中,然后在从头尾两端分别进行判断,
     * 直到两指针相遇或错过,如果中途不符合if判断直接返回false。除了使用链表，也可以使用栈，将链表的前半部分按次序压入栈中，
     * 再将后半部分逐一与栈顶比对,每次比对都弹出栈顶元素
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) return true;
        List<ListNode> list = new ArrayList<>();
        int length = 0;
        while (head != null) {
            list.add(head);
            length++;
            head = head.next;
        }
        for (int i = 0; i < length; i++, length--) {
            ListNode left = list.get(i);
            ListNode right = list.get(length - 1);
            if (left.val != right.val)
                return false;
        }
        return true;
    }
    /**
     * 方法二：
     *
     * 使用快慢指针找到链表的中间位置；
     * 反转后半部分链表；
     * 逐一对比前后两部分链表；
     * 与方法三比较接近，但没有方法三简便
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        // 边界条件：空链表或只有一个节点的链表
        if (head == null || head.next == null) {
            return true;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;

        // 慢指针一次走一步，快指针一次走两步，当快指针走到终点，慢指针刚好处于中点位置
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // fast指针置于下半段链表的起点
        fast = slow.next;
        // 断开前后两个链表
        slow.next = null;
        // slow指针置于前半段链表的起点
        slow = dummyNode.next;

        // 反转后半段链表
        ListNode pre = null; // 保存指针前一节点的信息，用于反转
        while (fast != null) {
            ListNode nextTemp = fast.next;
            fast.next = pre;
            pre = fast;
            fast = nextTemp;
        }

        // 前后半链表逐一比较，当链表长度为奇数时前半段链表长度比后半段多1，所以以后半段为准
        while (pre != null) {
            if (slow.val != pre.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }

    /**
     * 方法三：此方法思路是利用快慢指针将链表分为左右两部分
     * 对于slow与fast指针的位置有多种定位：
     *  1.如果是fast != null && fast.next != null 则将slow指针指向链表中点偏右(即如果链表长度是奇数,则slow指针指向中间；
     *  如果是偶数，则指向右半部分的开始)
     *  2.如果是fast.next != null && fast.next.next != null 则将slow指针指向链表中点偏左(即如果链表长度是奇数,则slow指针指向中间；
     *  如果是偶数，则指向左半部分的结尾)
     *  3.如果想要在链表长度为奇数时,从右半部分第一个节点处开始,可以在1）结束后再将slow右移一位，这样做的原因是当遇见检测回文链表时，
     *  中间节点不用检测，直接从左右相对的部分开始检测
     * 此方法为了检测方便，满足O(N)时间复杂度和O(1)的空间复杂度,在遍历链表时将左半部分翻转,有利于左右同时判断
     * @param head
     * @return
     */
    public boolean isPalindrome3(ListNode head) {
        if(head == null || head.next == null) return true;
        //将链表分为前后两部分，前半部分链表为逆序，后半部分保持不动，并从中间开始逐一比较
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        ListNode prevprev = null;  //为了翻转链表设置的节点，链接在prev节点后面
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
            //这两部必须写在最后，否则会是fast和slow指针移动产生异常
            prev.next = prevprev;
            prevprev = prev;

        }
        //链表长度是奇数时，将slow指针指向右边开始处节点
        if(fast != null)
            slow = slow.next;
        while(prev != null && slow != null){
            if(prev.val != slow.val)
                return false;
            prev = prev.next;
            slow = slow.next;
        }
        return true;
    }


}
