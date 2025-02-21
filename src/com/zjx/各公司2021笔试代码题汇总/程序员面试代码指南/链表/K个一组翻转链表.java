package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import java.io.*;

/**
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class K个一组翻转链表 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        String[] ss = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            cur = cur.next = new ListNode(Integer.parseInt(ss[i]));
        }
        int k = Integer.parseInt(br.readLine());
        ListNode res = reverseKNode(dummyHead,n,k);
        StringBuffer sb = new StringBuffer();
        while(res != null){
            sb.append(res.val + " ");
            res = res.next;
        }
        System.out.print(sb);
    }

    /**
     * 从左往右找到K个节点，断开链接并反转，拼接链表，重置指针和cnt，完成一次翻转
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1) return head;

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode cur = head;
        int cnt = k;
        while(cur != null) {
            //先找到第K个节点
            while(cur != null && --cnt > 0) {
                cur = cur.next;
            }
            //这里要小心如果写成cnt > 0可能会有空指针异常
            if(cur == null) {
                return dummyNode.next;
            }

            //预处理
            ListNode post = cur.next;
            cur.next = null;
            pre.next = reverse(pre.next);
            //后置处理 这里可以写为 cur.next = post;  pre = cur;  cur = post;
            while(pre.next != null) {
                pre = pre.next;
            }
            pre.next = post;
            cur = post;
            cnt = k;
        }
        return dummyNode.next;
    }

    private ListNode reverse(ListNode head) {
        if(head == null) return null;
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        return pre;
    }


    /**
     * 指针比较多   容易混乱
     */
    public static ListNode reverseKNode(ListNode head, int n, int k){  //head为哑节点
        if(head.next == null || head.next.next == null || k < 2 || k > n){
            return head;
        }
        ListNode fPre = head;  //左区间末尾
        ListNode end = fPre.next;  //表示翻转区间的末尾
        ListNode tPos;  //右区间开始  cur必不为空
        int count = 0;
        while(end != null){
            tPos = end.next;   //！！！！不要写进if中
            if(++count == k){
                ListNode start = fPre.next; //表示翻转区间的开始
                reverseList(fPre, tPos);
                fPre = start;
                count = 0;
            }
            end = tPos;  //！！！！注意不是cur = cur.next; 上面调整完之后cur的位置会改变，所以每次都要保存cur.next
        }
        return head.next;
    }

    private static void reverseList(ListNode fPre, ListNode tPos) {
        ListNode pre = tPos;
        ListNode cur = fPre.next;
        while(cur != tPos){
            ListNode post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        fPre.next = pre;
    }
}
