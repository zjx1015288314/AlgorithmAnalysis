package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表.合并;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表.ListNode;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 合并K个升序链表_困难 {

    /**
     * 时间复杂度O(nklogk)   空间复杂度O(1)   k为lists.length,n为链表长度
     * 思路：双指针从头尾开始两两合并，并把结果存放在第一个指针对应的位置，一轮循环后压缩一半空间
     * 最后直至right = 0
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;

        int left = 0;
        int right = lists.length - 1;
        while(right != 0) {
            while(left < right) {
                ListNode res = mergeKListsHelper(lists, left, right);
                lists[left] = res;
                left++;
                right--;
            }
            left = 0;
        }
        return lists[0];
    }

    public ListNode mergeKListsHelper(ListNode[] lists, int left, int right) {
        ListNode first = lists[left];
        ListNode second = lists[right];

        ListNode dummyNode = new ListNode(-1);
        ListNode pre = dummyNode;
        while(first != null && second != null) {
            if(first.val < second.val) {
                pre = pre.next = first;
                first = first.next;
            } else {
                pre = pre.next = second;
                second = second.next;
            }
        }
        pre.next = first != null ? first : second;
        return dummyNode.next;
    }
}
