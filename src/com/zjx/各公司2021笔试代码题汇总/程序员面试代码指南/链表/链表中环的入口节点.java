package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

/**
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 *
 * 数据范围： n≤1000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 *
 * @link https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 链表中环的入口节点 {

    /**
     * 快慢指针
     * 证明如下：
     * 假设链表有环，非环长度n，环部分分为b与c， b表示快慢指针相遇时超出环入口节点的距离   c为剩余距离
     * 快指针路程=a+(b+c)k+b ，k>=1  其中b+c为环的长度，k为绕环的圈数（k>=1,即最少一圈，不能是0圈，不然和慢指针走的一样长，矛盾）。
     * 慢指针路程=a+b (相遇时，快指针最坏情况为刚过入口节点，离走完一圈还有b+c-1步，也即落后慢指针b+c-1，由于每次快指针也慢指针多走
     * 一步，所以最坏情况下慢指针在入环b+c-1步后被追上，无法走完一圈)
     * 快指针走的路程是慢指针的两倍，所以：
     * （a+b）*2=a+(b+c)k+b
     * 化简可得：
     * a=(k-1)(b+c)+c 这个式子的意思是： 链表头到环入口的距离=相遇点到环入口的距离+（k-1）圈环长度。
     * 其中k>=1,所以k-1>=0圈。所以两个指针分别从链表头和相遇点出发，最后一定相遇于环入口。
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead == null) {return null;}
        ListNode fast = pHead;
        ListNode slow = pHead;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                break;
            }
        }
        if(fast.next == null || fast.next.next == null) {
            return null;
        }
        slow = pHead;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
