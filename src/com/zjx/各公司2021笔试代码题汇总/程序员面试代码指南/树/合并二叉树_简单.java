package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作
 * 为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 */
public class 合并二叉树_简单 {

    /**
     * 递归版本，以任一树作为基树,将另一个树融合到基树中
     */
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }

        root1.val += root2.val;
        root1.left = mergeTrees1(root1.left, root2.left);
        root1.right = mergeTrees1(root1.right, root2.right);
        return root1;
    }

    /**
     * 类似先序遍历,以TreeNode[]或者Pair<TreeNode, TreeNode>的形式保存左右子树节点
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if(t1 == null) return t2;
        if(t2 == null) return t1;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1, t2});
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            t[0].val += t[1].val;

            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else if(t[1].left != null){
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else if(t[1].right != null){
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }

    //层次遍历
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if(t1 == null) return t2;
        if(t2 == null) return t1;
        Queue<TreeNode[]> queue = new LinkedList<>();  //！！！！数组可以作为泛型
        queue.add(new TreeNode[] {t1, t2});
        while (!queue.isEmpty()) {
            TreeNode[] t = queue.poll();
            //t[0]为第一个树的节点，所以不为空的时候不会进栈，这里不用判断t[0] == null
            if (t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                queue.offer(new TreeNode[] {t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                queue.offer(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }

    //层次遍历普通写法
    public TreeNode mergeTrees4(TreeNode t1, TreeNode t2) {
        //如果 t1和t2中，只要有一个是null，函数就直接返回
        if(t1==null || t2==null) {
            return t1==null? t2 : t1;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(t1);
        queue.add(t2);
        while(!queue.isEmpty()) {
            TreeNode r1 = queue.poll();
            TreeNode r2 = queue.poll();
            r1.val += r2.val;
            //如果r1和r2的左子树都不为空，就放到队列中
            //如果r1的左子树为空，就把r2的左子树挂到r1的左子树上
            if(r1.left!=null && r2.left!=null){
                queue.offer(r1.left);
                queue.offer(r2.left);
            }
            else if(r1.left==null) {
                r1.left = r2.left;
            }
            //对于右子树也是一样的
            if(r1.right!=null && r2.right!=null) {
                queue.offer(r1.right);
                queue.offer(r2.right);
            }
            else if(r1.right==null) {
                r1.right = r2.right;
            }
        }
        return t1;
    }
}
