package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.二叉树路径;


import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

/**
 * 给定一个二叉树，请计算节点值之和最大的路径的节点值之和是多少。
 * 这个路径的开始节点和结束节点可以是二叉树中的任意节点
 * 例如：
 * 给出以下的二叉树，
 *     1
 *    / \
 *   2  3
 * 返回的结果为6
 * 输入 {-2,1}
 * 输出 1
 */
public class 二叉树最大路径和 {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // write code here
        if (root == null) return 0;
        process(root);
        return maxSum;
    }

    //分两部分考虑  路径和肯定是以某个根节点出发的  可能是单侧链   也可能是左右子树都有
    public int process(TreeNode root) {
        if (root == null) return 0;
        //!!!!!!注意这里递归调用的是process，而不是maxPathSum
        int leftMax = Math.max(process(root.left), 0);
        int rightMax = Math.max(process(root.right), 0);
        maxSum = Math.max(maxSum, root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }
}
