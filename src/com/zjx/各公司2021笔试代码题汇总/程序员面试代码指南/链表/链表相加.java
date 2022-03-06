package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class 链表相加 {
    static class Node {
        public int val;
        public Node next;
        public Node(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] str1 = input.readLine().split(" ");
        int n = Integer.valueOf(str1[0]);
        int m = Integer.valueOf(str1[1]);
        Node node1 = createNode(input.readLine().split(" "), n);
        Node node2 = createNode(input.readLine().split(" "), m);
        printNode(sumList(reverseList(node1),reverseList(node2)));
        reverseList(node1);  //不改变原有结构
        reverseList(node2);
    }

    //链表求和
    private static Node sumList(Node head1, Node head2) {
        int carry = 0;
        Node pre = null;
        Node curr = null;
        int x = 0;
        int y = 0;
        int sum = 0;
        while (head1 != null || head2 != null || carry != 0) {
            x = head1 == null ? 0 : head1.val;
            y = head2 == null ? 0 : head2.val;
            sum = x + y + carry;
            carry = sum / 10;
            curr = new Node(sum % 10);
            curr.next = pre;
            pre = curr;
            head1 = head1 != null ? head1.next : null;
            head2 = head2 != null ? head2.next : null;
        }
        return pre;
    }

    //反转链表
    private static Node reverseList(Node head) {
        if(head == null || head.next == null){
            return head;
        }
        Node curr = head;
        Node prev = null;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static Node createNode(String[] str, int n) {
        if(str == null || str.length == 0){
            return null;
        }
        Node head = new Node(Integer.parseInt(str[0]));
        Node node = head;
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(Integer.parseInt(str[i]));
            node.next = newNode;
            node = newNode;
        }
        return head;
    }



    private static void printNode(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(' ');
            head = head.next;
        }
        System.out.println(sb.toString());
    }
}
