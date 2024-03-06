package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.栈;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表.ListNode;

import java.util.Stack;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * 示例2：
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：翻转链表相加/栈
 */
public class 两数相加II {

    /**
     * 利用栈的特性
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while(l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while(l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        ListNode post = null;
        int digit = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || digit != 0) {
            int first = stack1.isEmpty() ? 0 : stack1.pop().val;
            int second = stack2.isEmpty() ? 0 : stack2.pop().val;
            int sum = first + second + digit;
            digit = sum / 10;
            ListNode cur = new ListNode(sum % 10);
            cur.next = post;
            post = cur;
        }
        return post;
    }

    /**
     * 翻转链表再相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        int d = 0;
        ListNode post = null;
        while(l1 != null || l2 != null || d != 0){
            if(l1 != null) d += l1.val;
            if(l2 != null) d += l2.val;
            ListNode curr = new ListNode(d % 10);
            curr.next = post;
            post = curr;
            d /= 10;
            l1 = l1 != null? l1.next : null;
            l2 = l2 != null? l2.next : null;
        }
        return post;
    }

    private ListNode reverseList(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }
}
