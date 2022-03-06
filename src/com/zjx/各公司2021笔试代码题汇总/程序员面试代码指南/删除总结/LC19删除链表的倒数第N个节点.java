package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;

public class LC19删除链表的倒数第N个节点 {
    class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev,end;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        prev = dummy;
        end = head;
        for(int i = 1; i < n; i++) {
            if (end == null) return null;   //链表长度小于n
            end = end.next;
        }

        while(end.next != null){
            prev = prev.next;
            end = end.next;
        }
        prev.next = prev.next.next;
        return dummy.next;
    }
}
