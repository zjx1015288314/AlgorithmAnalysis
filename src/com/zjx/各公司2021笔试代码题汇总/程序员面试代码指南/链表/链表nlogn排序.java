package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 这个题主要考察的是排序思想的应用，虽然可能大部分都知道数组上的排序，但是如果用到链表上，
 * 可能就会有人一时想不起来，这道题要求O(nlogn）的算法，所以考虑快排，归并，堆排，
 * 堆排个人认为不怎么好写，这里不做考虑，主要讲一下快排，归并
 */
public class 链表nlogn排序 {
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
//        ListNode res = sortList(dummy.next);
        quickSort(dummy.next,null);
        ListNode res = dummy.next;
        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    /**
     * 递归  归并排序
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {  //快慢指针分割
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur = cur.next = left;
                left = left.next;
            } else {
                cur = cur.next = right;
                right = right.next;
            }
        }
        cur.next = left != null ? left : right;
        return dummyHead.next;
    }

    /**
     * 迭代  归并排序
     * @param head
     * @return
     */
    public ListNode sortList1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //1.统计链表长度
        int length = 0;
        while(head != null){
            length++;
            head = head.next;
        }

        //切割logn次  每次切割的长度递增
        for(int i = 1; i < length; i += i){
            head = dummy.next;
            ListNode pre = dummy;
            ListNode first;
            ListNode second;
            while(head != null){
                first = head;
                second = head = cutFromHead(head,i);
                head = cutFromHead(head,i);
                pre.next = mergeLists(first,second);
                while(pre.next != null) pre = pre.next;
            }
        }
        return dummy.next;

    }

    //将链表从head节点开始的n个节点切断，并返回第n+1个节点
    public ListNode cutFromHead(ListNode head,int n){
        while(head != null && --n > 0){
            head = head.next;
        }
        if(head == null) return null;
        ListNode next = head.next;
        head.next = null;
        return next;
    }

    public ListNode mergeLists(ListNode first,ListNode second){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(first != null && second != null){
            if(first.val < second.val){
                cur = cur.next = first;
                first = first.next;
            }else{
                cur = cur.next = second;
                second = second.next;
            }
        }
        cur.next = first != null? first : second;
        return dummy.next;
    }

    /**
     * 快排的写法，用了快排的思想，以及交换值
     * 快排时间复杂度O(nlogn),最坏O(n^2):如果输入的链表已经是倒序的，但是要通过快排实现正序，且pivot选择第一个元素，则达到最坏情况
     * 所以pivot的选择很重要，一般选择中间的元素
     * 空间复杂度O(logn),主要是递归深度
     * 不稳定，因为会涉及前后交换
     * @param head
     * @param tail
     */
    public static void quickSort(ListNode head, ListNode tail){
        if (head == tail) return;
        ListNode mid = partition(head,tail);
        if(head != mid) quickSort(head,mid);
        if(mid.next != tail)quickSort(mid.next,tail);
    }

    public static ListNode partition(ListNode head,ListNode tail){
        ListNode cur = head;
        ListNode post = head.next;
        // tail 为null
        while (post != tail){
            if(post.val < head.val){
                cur = cur.next;
                swap(cur,post);
            }
            post = post.next;
        }
        swap(cur,head);
        return cur;  //左部分的右边界
    }

    public static void swap(ListNode l,ListNode r){
        int tmp = l.val;
        l.val = r.val;
        r.val = tmp;
    }
}
