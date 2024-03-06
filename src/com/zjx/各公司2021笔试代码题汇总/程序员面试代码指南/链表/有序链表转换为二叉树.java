package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 有序链表转换为二叉树 {

    public static void main(String[] args) {
        int[] nums = {-10, -5, 0, 1, 3};
        ListNode dummyNode = new ListNode(-1);
        ListNode pre = dummyNode;
        for (int i = 0; i < 5; i++) {
            ListNode node = new ListNode(nums[i]);
            pre = pre.next = node;
        }
        sortedListToBST(dummyNode.next);
    }

    /**
     * 方法一 思路是把链表分为左、根、右三部分，利用递归的方式构建二叉搜索树
     * 注意链表长度<=2时，左子树为空(即head与slow指向相同)
     * @param head
     * @return
     */
    private static TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;

        ListNode pre = null;
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;

        }
        fast = slow.next;
        //分割中间节点
        slow.next = null;
        if(pre != null) {
            pre.next = null;
        }

        TreeNode root = new TreeNode(slow.val);
        //这时会发生栈溢出，所以此种情况root.left = null
        root.left = head == slow ? null : sortedListToBST(head);
        root.right = sortedListToBST(fast);
        return root;
    }

    /**
     * 方法二   参照有序数组转换为链表的方式，先把链表转换为数组
     */
    List<Integer> values = new ArrayList<Integer>();
    public TreeNode sortedListToBST2(ListNode head) {
        mapListToValues(head);
        return convertListToBST(0, this.values.size() - 1);
    }

    private TreeNode convertListToBST(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        //与中序遍历不同,这里直接可以获得mid处节点
        TreeNode node = new TreeNode(this.values.get(mid));

        //中序遍历方法没有这步,为了使head节点后移,即使是一个节点，也要执行一次方法使head节点后移
        if (left == right) {
            return node;
        }

        node.left = convertListToBST(left, mid - 1);
        node.right = convertListToBST(mid + 1, right);
        return node;
    }

    private void mapListToValues(ListNode head) {
        while (head != null) {
            this.values.add(head.val);
            head = head.next;
        }
    }
}
