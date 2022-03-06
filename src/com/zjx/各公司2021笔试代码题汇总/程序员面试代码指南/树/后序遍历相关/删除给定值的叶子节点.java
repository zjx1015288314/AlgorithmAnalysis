package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.后序遍历相关;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.Stack;

/**
 * 给你一棵以 root 为根的二叉树和一个整数 target ，请你删除所有值为 target 的 叶子节点 。
 * 注意，一旦删除值为 target 的叶子节点，它的父节点就可能变成叶子节点；
 * 如果新叶子节点的值恰好也是 target ，那么这个节点也应该被删除。
 * 也就是说，你需要重复此过程直到不能继续删除。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-leaves-with-a-given-value
 */
public class 删除给定值的叶子节点 {
    /**
     * 栈的后序遍历基础上修改
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) return null;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = new TreeNode(-1);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if(cur.left != null && pre != cur.left && pre != cur.right) {
                stack.push(cur.left);
            } else if(cur.right != null && pre != cur.right) {
                stack.push(cur.right);
            } else {
                /*******************修改的地方*********************/
                if(cur.left != null && isTargetLeafNode(cur.left, target)) {
                    cur.left = null;
                }
                if(cur.right != null && isTargetLeafNode(cur.right, target)) {
                    cur.right = null;
                }
                /**************************************************/
                pre = stack.pop();
            }
        }
        return isTargetLeafNode(pre, target) ? null : pre;
    }

    private boolean isTargetLeafNode(TreeNode root, int target) {
        if(root == null) return false;

        if(root.left == null && root.right == null && root.val == target) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 递归版本
     */
    public TreeNode removeLeafNodes1(TreeNode root, int target) {
        if(root == null) return null;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if(root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }
}
