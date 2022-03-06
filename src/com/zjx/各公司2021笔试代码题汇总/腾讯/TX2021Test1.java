package com.zjx.各公司2021笔试代码题汇总.腾讯;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 两个升序链表的公共部分打印出来
 */
public class TX2021Test1 {
    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);

        ListNode dummy1 = new ListNode(-1);
        ListNode cur = dummy1;
        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            cur = cur.next = new ListNode(Integer.parseInt(str[i]));
        }

        str = br.readLine().split(" ");
        int m = Integer.parseInt(str[0]);
        ListNode dummy2 = new ListNode(-1);
        cur = dummy2;
        str = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            cur = cur.next = new ListNode(Integer.parseInt(str[i]));
        }

        ListNode res = process(dummy1.next,dummy2.next);
        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    private static ListNode process(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (head1 != null && head2 != null){
            if (head1.val > head2.val){
                head1 = head1.next;
            }else if (head1.val < head2.val){
                head2 = head2.next;
            }else{
                cur = cur.next = head1;
                head1 = head1.next;
                cur.next = null;
                head2 = head2.next;
            }
        }
        return dummyHead.next;
    }
}
