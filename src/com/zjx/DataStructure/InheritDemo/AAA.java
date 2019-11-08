package com.zjx.DataStructure.InheritDemo;

public class AAA {
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode first = head;
        ListNode second = head.next;
        dummy.next = second;
        while(first != null && second != null){
            ListNode succ = second.next;
            first.next =  (succ != null && succ.next != null)? succ.next:succ;
            second.next = first;
            first = succ;
            second = succ != null? succ.next:null;
        }
        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        System.out.println(swapPairs(head));
    }
}
