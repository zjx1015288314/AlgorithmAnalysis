package com.zjx.DataStructure.Chapter3;

/**
 * 给你一个链表的头节点head,请你编写代码，反复删去链表中由 总和值为0的连续节点组成的序列，直到不存在这样的序列为止。
 * 删除完毕后，请你返回最终结果链表的头节点。
 * 你可以返回任何满足题目要求的答案。(注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示)
 * 示例 1：
 * 输入：head = [1,2,-3,3,1]
 * 输出：[3,1]
 * 提示：答案 [1,2,1] 也是正确的。
 * 示例 2：
 * 输入：head = [1,2,3,-3,4]
 * 输出：[1,2,4]
 * 示例 3：
 * 输入：head = [1,2,3,-3,-2]
 * 输出：[1]
 * 提示：
 * 给你的链表中可能有 1 到 1000 个节点。
 * 对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000.
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list
 */
public class RemoveZeroSumSublists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 这道题没有想出来的原因在于做题前一直想要控制时间复杂度在O(NlogN)之下,有些题目不得不考虑O(N^2)的做法，否则无法实现
     * 思路：选择某一节点为prev节点，如果其后cur节点到succ节点的值的和为0，则prev.next = succ.next,否则prev = prev.next
     * 这里使用flag变量来指明是否需要
     *
     * @param head
     * @return
     */
    public ListNode removeZeroSumSublists1(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        boolean flag;
        ListNode cur = null;
        while (prev.next != null) {
            flag = true;
            cur = prev.next;
            if (cur.val == 0) {
                prev.next = cur.next;
                continue;
            }
            int sum = cur.val;
            ListNode succ = cur.next;
            while (succ != null) {
                sum += succ.val;
                if (sum == 0) {
                    prev.next = succ.next;
                    flag = false;
                    break;
                }
                succ = succ.next;
            }
            if (flag) prev = prev.next;
        }
        return dummy.next;
    }

    /**
     * 上面的内容比较繁琐，而且prev指针与cur指针有一个固定关系:cur = prev.next 所以删除cur指针改用prev.next代替
     * 还有一点是succ从cur开始(即prev.next)而不是prev.next.next,所以删除flag及其相关判断,将某个节点值为0和一连串
     * 节点值的和为0两种情况一起处理,更加简单
     *
     * @param head
     * @return
     */
    public ListNode removeZeroSumSublists2(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null) {
            ListNode cur = prev.next;
            int sum = 0;
            while (cur != null) {
                sum += cur.val;
                if (sum == 0)
                    break;
                cur = cur.next;
            }
            if (sum == 0) {
                prev.next = cur.next;
                continue;
            }
            prev = prev.next;
        }
        return dummy.next;
    }
}
