package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.层序遍历;

/**
 * 给定一个二叉树
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有next 指针都被设置为 NULL。

 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 填充二叉树每个节点的下一个右侧节点指针II {

    /**
     * 自己的解法， first与pre指针的处理有点复杂
     */
    public Node connect1(Node root) {
        if(root == null) return null;
        Node cur = root;
        while(cur != null){
            Node first = null;
            Node pre = null;
            while(cur != null){
                if(cur.left != null) {
                    if(pre != null) {
                        pre = pre.next = cur.left;
                    }
                    if(first == null){
                        first = cur.left;
                        pre = first;
                    }
                }
                if(cur.right != null) {
                    if(pre != null) {
                        pre = pre.next = cur.right;
                    }
                    if(first == null){
                        first = cur.right;
                        pre = first;
                    }
                }
                cur = cur.next;
            }
            cur = first;
        }
        return root;
    }

    /**
     * 改进版本，使用哑结点解决了每一层头结点不好找的问题
     */
    public Node connect2(Node root) {
        Node cur = root;
        while(cur != null){
            Node dummy = new Node();
            Node tail = dummy;
            while(cur != null){
                if(cur.left != null){
                    tail = tail.next = cur.left;
                }
                if(cur.right != null){
                    tail = tail.next = cur.right;
                }
                cur = cur.next;
            }
            cur = dummy.next;
        }
        return root;
    }
}
