package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;


/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 * 与83区别在于可能会删除头结点，所以用哑结点作了处理
 */
public class LC82删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode next = cur.next;
        while(next != null){
            //这样做的原因是，因为不知道前后不一致之前节点是否重复，所以需要在cur.val == next.val时找到所有
            if(cur.val == next.val){
                while(next != null && cur.val == next.val){next = next.next;}
                cur = next;
                pre.next = cur;

                next = cur == null ? null : cur.next;
            }else{
                pre = cur;
                cur = next;
                next = next.next;
            }
        }
        return dummy.next;
    }

    /**
     * 删除了next指针
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication2(ListNode pHead) {
        if(pHead == null || pHead.next == null) return pHead;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = pHead;
        ListNode pre = dummyNode;
        ListNode cur = pre.next;
        while(cur != null){
            if(cur.next != null && cur.next.val == cur.val){
                // 相同结点一直前进
                while(cur.next != null && cur.next.val == cur.val){
                    cur = cur.next;
                }
                // 退出循环时，cur 指向重复值，也需要删除，而 cur.next 指向第一个不重复的值
                // cur 继续前进
                cur = cur.next;
                // pre 连接新结点
                pre.next = cur;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
