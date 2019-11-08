package com.zjx.DataStructure.Charpter4;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表,返回链表开始入环的第一个节点.如果链表无环,则返回 null
 * 为了表示给定链表中的环,我们使用整数 pos 来表示链表尾连接到链表中的位置(索引从 0 开始). 如果 pos 是 -1,则在该链表中没有环
 * 说明：不允许修改给定的链表。
 * LeetCode链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 */
public class detectCycle {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    /**
     * 方法 1：哈希表 时间复杂度:O(n) 空间复杂度:O(n)
     * 如果我们用一个 Set 保存已经访问过的节点,我们可以遍历整个列表并返回第一个出现重复的节点
     * 算法:
     *  首先,我们分配一个 Set 去保存所有的列表节点.我们逐一遍历列表,检查当前节点是否出现过,如果节点已经出现过,那么
     *  一定形成了环且它是环的入口.否则如果有其他点是环的入口,我们应该先访问到其他节点而不是这个节点.其他情况,没有成环则直接返回 null
     *  算法会在遍历有限个节点后终止,这是因为输入列表会被分成两类:成环的和不成环的.一个不成欢的列表在遍历完所有节点后会到达 null -
     *  即链表的最后一个元素后停止.一个成环列表可以想象成是一个不成环列表将最后一个 null 元素换成环的入口
     *  如果 while 循环终止,我们返回 null 因为我们已经将所有的节点遍历了一遍且没有遇到重复的节点,这种情况下,列表是不成环的
     *  对于循环列表, while 循环永远不会停止,但在某个节点上,if 条件会被满足并导致函数的退出
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        Set<ListNode> nodeSeen = new HashSet<>();
        while (head != null) {
            if (nodeSeen.contains(head))
                return head;
            else
                nodeSeen.add(head);
            head = head.next;
        }
        return null;
    }


    /**
     * 方法 2:Floyd 算法
     * 算法:
     * Floyd 的算法被划分成两个不同的阶段.在第一阶段,找出列表中是否有环,如果没有环,可以直接返回 null并退出.否则,用相遇节点来找到环的入口
     * 阶段 1:
     *  这里我们初始化两个指针 - 快指针和慢指针.我们每次移动慢指针一步、快指针两步,直到快指针无法继续往前移动
     *  如果在某次移动后,快慢指针指向了同一个节点,我们就返回它.否则,我们继续,直到 while 循环终止且没有返回任何节点,这种情况说明没有成环,我们返回 null
     * 阶段 2:
     *  给定阶段 1 找到的相遇点,阶段 2 将找到环的入口.首先我们初始化额外的两个指针: ptr1指向链表的头， ptr2 指向相遇点.然后,我们每次将它们往前移动一步,
     *  直到它们相遇,它们相遇的点就是环的入口,返回这个节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // 依照两指针相遇的地点找到入口,这里涉及到数学公式,两指针相遇时的地点是C-X,距离入口为X，而链表非环形部分长度也为X,
        // 所以再设置两个指针,一个指向链表头部,一个指向相遇位置,让它们同时移动,两指针相遇时记为环形入口
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }
    private ListNode getIntersect(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
}
