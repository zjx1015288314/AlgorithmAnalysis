package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class 链表相加 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] str1 = input.readLine().split(" ");
        int n = Integer.valueOf(str1[0]);
        int m = Integer.valueOf(str1[1]);
        ListNode ListNode1 = createListNode(input.readLine().split(" "), n);
        ListNode ListNode2 = createListNode(input.readLine().split(" "), m);
        printListNode(sumList(reverseList(ListNode1),reverseList(ListNode2)));
        reverseList(ListNode1);  //不改变原有结构
        reverseList(ListNode2);
    }

    //链表求和
    private static ListNode sumList(ListNode head1, ListNode head2) {
        int carry = 0;
        ListNode post = null;
        ListNode curr = null;
        int x = 0;
        int y = 0;
        int sum = 0;
        while (head1 != null || head2 != null || carry != 0) {
            x = head1 == null ? 0 : head1.val;
            y = head2 == null ? 0 : head2.val;
            sum = x + y + carry;
            carry = sum / 10;
            curr = new ListNode(sum % 10);
            curr.next = post;
            post = curr;
            head1 = head1 != null ? head1.next : null;
            head2 = head2 != null ? head2.next : null;
        }
        return post;
    }

    //反转链表
    private static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode createListNode(String[] str, int n) {
        if(str == null || str.length == 0){
            return null;
        }
        ListNode head = new ListNode(Integer.parseInt(str[0]));
        ListNode ListNode = head;
        for (int i = 1; i < n; i++) {
            ListNode newListNode = new ListNode(Integer.parseInt(str[i]));
            ListNode.next = newListNode;
            ListNode = newListNode;
        }
        return head;
    }



    private static void printListNode(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(' ');
            head = head.next;
        }
        System.out.println(sb.toString());
    }
}
