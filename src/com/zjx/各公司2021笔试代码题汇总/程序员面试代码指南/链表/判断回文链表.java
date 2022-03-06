package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import java.util.Scanner;

/**
 * @author zhaojiexiong
 * @create 2020/6/10
 * @since 1.0.0
 */
public class 判断回文链表 {
    static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }
    static Node lEnd = null;
    static Node rStart = null;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        Node head = new Node(-1);
        Node cur = head;
        for (int i = 0; i < len; i++) {
            cur = cur.next = new Node(Integer.valueOf(in.nextInt()));
        }
        if(head.next == null){
            throw new RuntimeException("Node cannot be null");
        }
        rStart = head.next;
        lEnd = head.next;
        while(rStart.next != null && rStart.next.next != null){
            rStart = rStart.next.next;
            lEnd = lEnd.next;
        }
        rStart = len % 2 == 0 ? lEnd.next : lEnd;
        boolean flag = isPalindrome(head.next);
        System.out.print(flag);

    }
    public static boolean isPalindrome(Node left){
        boolean flag = true;
        if(left != lEnd){
            flag = isPalindrome(left.next);
        }
        if(flag && left.val == rStart.val){
            rStart = rStart.next;
            return true;
        }
        return false;
    }
}
