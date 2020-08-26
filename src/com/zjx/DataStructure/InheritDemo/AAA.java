package com.zjx.DataStructure.InheritDemo;

import sun.rmi.runtime.Log;

import java.util.Arrays;

public class AAA {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode first = head;
        ListNode second = head.next;
        dummy.next = second;
        while (first != null && second != null) {
            ListNode succ = second.next;
            first.next = (succ != null && succ.next != null) ? succ.next : succ;
            second.next = first;
            first = succ;
            second = succ != null ? succ.next : null;
        }
        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        System.out.println(swapPairs(head));


        //该方式比较特殊,两个引用指向同一个地址
        int[] lNumbers1 = new int[5];
        int[] rNumbers1 = lNumbers1;
        rNumbers1[0] = 1;
        boolean first = lNumbers1[0] == rNumbers1[0];
        System.out.println("lNumbers1[0]=" + lNumbers1[0] + ",rNumbers1[0]=" + rNumbers1[0] + "---" + first);

        int[] lNumbers2 = new int[5];
        int[] rNumbers2 = Arrays.copyOf(lNumbers2, lNumbers2.length);
        rNumbers2[0] = 1;
        boolean second = lNumbers2[0] == rNumbers2[0];
        System.out.println("lNumbers2[0]=" + lNumbers2[0] + ",rNumbers2[0]=" + rNumbers2[0] + "---" + second);

        int[] lNumbers3 = new int[5];
        int[] rNumbers3 = lNumbers3.clone();
        rNumbers3[0] = 1;
        boolean third = lNumbers3[0] == rNumbers3[0];
        System.out.println("lNumbers3[0]=" + lNumbers3[0] + ",rNumbers3[0]=" + rNumbers3[0] + "---" + third);

    }
}
