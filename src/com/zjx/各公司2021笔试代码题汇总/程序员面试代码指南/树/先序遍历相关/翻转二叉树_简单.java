package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.先序遍历相关;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.Stack;

/**
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 翻转二叉树_简单 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;

            if(cur.left != null) {
                stack.push(cur.left);
            }
            if(cur.right != null) {
                stack.push(cur.right);
            }
        }
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        //先序遍历的迭代的一次写法
        while(!stack.isEmpty() || cur != null){
            if(cur != null){
                //先序的处理写在这里
                TreeNode tmp = cur.right;
                cur.right = cur.left;
                cur.left = tmp;
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                //do something   中序的处理写在这里
                //TreeNode tmp = cur.right;
                //cur.right = cur.left;
                //cur.left = tmp;
                cur = cur.right;
            }
        }
        return root;
    }
}
