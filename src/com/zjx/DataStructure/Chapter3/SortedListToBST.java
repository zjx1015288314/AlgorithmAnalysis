package com.zjx.DataStructure.Chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode链接:https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/submissions/
 */
public class SortedListToBST {
    /**
     * 链表节点和树节点
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }



    /**
     * 方法一:自己思路与官方的思路大致一样,但是在细节的处理方面不够好,看起来代码很乱
     * @param head
     * @return
     */
    public TreeNode sortedListToBST1(ListNode head) {
        if(head == null) return null;
        ListNode mid,end,prev;
        mid = end = head;
        prev = null;

        int n = 2;
        while(end.next != null){
            n--;
            end = end.next;
            if(n == 0){
                prev = mid;
                mid = mid.next;
                n = 2;
            }
        }
        //the root of tree which will be returned
        TreeNode root = new TreeNode(mid.val);

        //处理只有一个节点的情况
        if(mid == end) return root;
        //处理只有两个节点的情况,这里之所以与官网情况不一样是因为该解法的end节点指向最后一个非null节点,导致prev,mid必须要单独考虑两个节点时的情况
        //否则会造成死循环(一直在处理两个节点);可以像官方解法一样,end节点指向最后的null节点
        if(mid == head && mid.next == end){
            root.right = new TreeNode(end.val);
            return root;
        }
        if (prev != null) prev.next = null;
        root.left = sortedListToBST1(head);
        root.right = sortedListToBST1(mid.next);
        return root;
    }
    /**
     * 方法二:官方解法,利用findMiddleElement()找到中间节点,并将链表分为左右两段,分为递归地构造左右子树
     * @param head
     * @return
     */
    private ListNode findMiddleElement(ListNode head) {
        //快慢指针,fastPtr一次走两步,slowPtr一次走一步
        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Iterate until fastPr reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }

    public TreeNode sortedListToBST2(ListNode head) {

        // If the head doesn't exist, then the linked list is empty
        if (head == null) {
            return null;
        }

        // Find the middle element for the list.
        ListNode mid = this.findMiddleElement(head);

        // The mid becomes the root of the BST.
        TreeNode node = new TreeNode(mid.val);

        // Base case when there is just one element in the linked list
        if (head == mid) {
            return node;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = this.sortedListToBST2(head);
        node.right = this.sortedListToBST2(mid.next);
        return node;
    }
    /**
     * 方法三:官方中序遍历解法,最需要注意的是head如何一步步在递归中前进到合适的位置(将head设置为类的属性是必须的,
     * 为了使某一层head的变化影响到上一层),以及为什么不像数组中那样,有if(left == right)这种直接返回的语句?
     * 这样做使得即使只有一个元素,也要进行一次递归,并将head后移
     */
    private ListNode head;

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST3(int l, int r) {
        // Invalid case,这里保证了即使是单个节点也要进入一次该方法
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST3(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST3(mid + 1, r);
        return node;
    }

    public TreeNode sortedListToBST3(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST3(0, size - 1);
    }


    /**
     * 这个方法是空间换时间的经典案例.在这个方法中,我们将给定的链表转成数组并利用数组来构建二叉搜索树
     * 数组找中间元素只需要 O(1) 的时间,所以会降低整个算法的时间复杂度开销
     * 算法:
     *  1.将给定链表转成数组,将数组的头和尾记成 left 和 right
     *  2.找到中间元素 (left + right) / 2,记为 mid.这需要 O(1) 时间开销,也是与上面算法主要改进的地方
     *  3.将中间元素作为二叉搜索树的根
     *  4.递归构造二叉搜索树的左右两棵子树,两个子数组分别是 (left, mid - 1) 和 (mid + 1, right)
     *
     */
    private List<Integer> values;

    public void Solution() {
        this.values = new ArrayList<Integer>();
    }

    private void mapListToValues(ListNode head) {
        while (head != null) {
            this.values.add(head.val);
            head = head.next;
        }
    }

    private TreeNode convertListToBST(int left, int right) {
        // Invalid case
        if (left > right) {
            return null;
        }

        // Middle element forms the root.
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(this.values.get(mid));

        // Base case for when there is only one element left in the array
        if (left == right) {
            return node;
        }

        // Recursively form BST on the two halves
        node.left = convertListToBST(left, mid - 1);
        node.right = convertListToBST(mid + 1, right);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {

        // Form an array out of the given linked list and then
        // use the array to form the BST.
        this.mapListToValues(head);

        // Convert the array to
        return convertListToBST(0, this.values.size() - 1);
    }


}
