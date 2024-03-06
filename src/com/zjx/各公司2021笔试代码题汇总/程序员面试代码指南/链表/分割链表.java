package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 链表中奇数次序的节点放前面，偶数次序的放后面
 */
public class 分割链表 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            cur = cur.next = new ListNode(Integer.parseInt(str[i]));
        }
        ListNode res = partition(dummy.next);
        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
    public static ListNode partition(ListNode head) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode pre1 = dummy1;
        ListNode pre2 = dummy2;

        int i = 1;
        while(head != null){
            if(i++ % 2 == 1){
                pre1 = pre1.next = head;
            }else{
                pre2 = pre2.next = head;
            }
            head = head.next;
        }
        pre2.next = null;
        pre1.next = dummy2.next;
        return dummy1.next;
    }
}
