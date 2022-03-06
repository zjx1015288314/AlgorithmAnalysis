package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author zhaojiexiong
 * @create 2020/6/6
 * @since 1.0.0
 */


public class PrintListsCommonPart {
    static class Node {
    public int val;
    public Node next;

    public Node(int val) {
        this.val = val;
    }
}
    public static void main(String[] args) throws IOException {
//        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        //String[] str = reader.readLine().split(" ");
//        Scanner in = new Scanner(System.in);
//        int len1 = in.nextInt();
//        Node head1 = new Node(-1);
//        Node cur = head1;
//        for (int i = 0; i < len1; i++) {
//            cur = cur.next = new Node(in.nextInt());
//        }
//        int len2 = in.nextInt();
//        Node head2 = new Node(-1);
//        cur = head2;
//        for (int i = 0; i < len2; i++) {
//            cur = cur.next = new Node(in.nextInt());
//        }
//        head1 = head1.next;
//        head2 = head2.next;
//        while (head1 != null && head2 != null) {
//            if (head1.val < head2.val) {
//                head1 = head1.next;
//            } else if (head1.val > head2.val) {
//                head2 = head2.next;
//            } else {
//                System.out.print(head1.val + " ");
//                head1 = head1.next;
//                head2 = head2.next;
//            }
//        }
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int lastKth = in.nextInt();
        Node head = new Node(-1);
        Node cur = head;
        for (int i = 0; i < len; i++) {
            cur = cur.next = new Node(in.nextInt());
        }

        cur = head.next;
        if(cur == null || lastKth < 1){
            return;
        }
        while(cur != null){
            lastKth--;
            cur = cur.next;
        }
        if(lastKth == 0){
            head.next = head.next.next;
        }
        if(lastKth < 0){
            cur = head.next;
            while(++lastKth < 0){
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        cur = head.next;
        while(cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        StringBuffer sb = new StringBuffer();
    }

}
