package com.zjx.DataStructure.Chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * LeetCode链接：https://leetcode-cn.com/problems/reorder-list
 */
public class ReorderList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 方法一：递归,利用两个指针指向头尾节点(通过一次遍历找到尾结点),完成处理后,将链表的剩余部分再进行翻转
     * 时间复杂度：O(N^2),每次为了找到尾结点都需要对链表进行一次遍历
     * 空间复杂度：O(N),递归的调用栈深度为N
     * @param head
     */
    public void reorderList1(ListNode head) {
        orderList1(head);
    }

    public ListNode orderList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode end = head;
        ListNode prev = null;
        while (end.next != null) {
            prev = end;
            end = end.next;
        }
        prev.next = null;
        end.next = orderList1(head.next);
        head.next = end;
        return head;
    }

    /**
     * 方法二：递归+回溯 同样是先处理头尾节点再处理剩余部分,但这次是尾结点直接找到它的前一个节点而不需要遍历链表
     * 递归的终止条件是尾结点为空,且当两指针相遇或错过时,返回；具体流程可以参考官方解析：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode/
     */
    private ListNode left;
    private boolean stop;
    public void reorderList2(ListNode head) {
        left = head;
        stop = false;
        orderList2(head);
    }

    public void orderList2(ListNode right){
        if(right == null) return;
        orderList2(right.next);
        if(this.left == right || right.next == this.left){
            left.next = null;
            this.stop = true;
        }

        if(!this.stop){
            ListNode succ = this.left.next;
            right.next = succ;
            this.left.next = right;
            this.left = succ;
        }
    }

    /**
     * 方法3：递归,与方法2相似
     * @param head
     */
    public void reorderList3(ListNode head) {

        //0/1/2个节点时直接返回
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        ListNode h = head;
        //求出节点数
        while (h != null) {
            len++;
            h = h.next;
        }
        reorderListHelper(head, len);
    }

    /**
     * 返回head指针所在层的外层的尾结点
     * @param head
     * @param len
     * @return
     */
    private ListNode reorderListHelper(ListNode head, int len) {
        //head指针剩余部分的首节点
        if (len == 1) {
            ListNode outTail = head.next; //内层尾结点的next指向外层的尾结点
            head.next = null;
            return outTail;
        }
        if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        ListNode subHead = head.next;//中间链表的头结点
        head.next = tail;
        ListNode outTail = tail.next;  //上一层 head 对应的 tail
        tail.next = subHead;
        return outTail;
    }

    /**
     * 主要是三步，举个例子。
     *
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * 第一步，将链表平均分成两半
     * 1 -> 2 -> 3
     * 4 -> 5 -> 6
     *
     * 第二步，将第二个链表逆序
     * 1 -> 2 -> 3
     * 6 -> 5 -> 4
     *
     * 第三步，依次连接两个链表
     * 1 -> 6 -> 2 -> 5 -> 3 -> 4
     *
     */
    public void reorderList4(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        //找中点，链表分成两个
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;   //从slow处断开链表，且左链表长度>=右链表长度

        //第二个链表倒置
        newHead = reverseList(newHead);

        //链表节点依次连接,因为第一个链表长于第二个链表，所以只对第二个链表进行null判断
        while (newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = temp;
        }

    }

    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = head;
        ListNode succ = head.next;

        while(succ != null){
            curr.next = prev;
            prev = curr;
            curr = succ;
            succ = curr.next;
        }
        curr.next = prev;
        return curr;
    }

    /**
     * 链表的缺点就是不能随机存储，当我们想取末尾元素的时候，只能从头遍历一遍，很耗费时间。
     * 第二次取末尾元素的时候，又得遍历一遍。
     *
     * 所以先来个简单粗暴的想法，把链表存储到线性表中，然后用双指针依次从头尾取元素即可。
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        //存到 list 中去
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        //头尾指针依次取元素
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            //偶数个节点的情况，会提前相遇
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }



}
