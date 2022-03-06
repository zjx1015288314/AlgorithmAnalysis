package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * 如何删除单链表中的重复节点（即保证每个元素只出现一次，删除多余的，且后来出现的元素）。
 * 一个没有排序的单链表，如 list = {a, 1, x, b, e, f, f, e, a, g, h, b, m}，请去掉重复项，
 * 并保留原顺序，以上链表去掉重复项后为 newList = {a, 1, x, b, e, f, g, h, m}，请写出一个高效的算法。
 */
public class 删除非排序单链表中的重复节点 {
    static class ListNode{
        char val;
        ListNode next;
        public ListNode(char val){
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int len = Integer.parseInt(str[0]);

        str = br.readLine().split(" ");
        ListNode dummyHead = new ListNode('0');
        ListNode cur = dummyHead;
        for (int i = 0; i < len; i++) {
            cur = cur.next = new ListNode(str[i].charAt(0));
        }

//        ListNode res = process(dummyHead.next); //通过
        ListNode res = process1(dummyHead.next);
        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    /**
     * HashSet保存出现过的链表元素   时间复杂度O(N)   空间复杂度O(N)
     * @param head
     * @return
     */
    public static ListNode process(ListNode head){
        if(head == null || head.next == null) return head;
        HashSet<Character> set = new HashSet<>();
        set.add(head.val);
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            if (set.contains(cur.val)){
                pre.next = cur.next;
            }else{
                set.add(cur.val);
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * O(N^2)
     * @param head
     * @return
     */
    public static ListNode process1(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode first = head;
        ListNode pre = null;
        ListNode second = null;
        while (first != null){
            pre = first;
            second = first.next;
            while (second != null){
                if(second.val == first.val){
                    pre.next = second.next;
                }else{
                    pre = second;
                }
                second = second.next;
            }
            first = first.next;
        }
        return head;
    }
}
