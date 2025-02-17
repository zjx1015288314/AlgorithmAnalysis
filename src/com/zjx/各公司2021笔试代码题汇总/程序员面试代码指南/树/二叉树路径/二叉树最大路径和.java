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
 * 链接：https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/
 */
public class 二叉树最大路径和 {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // write code here
        if (root == null) return 0;
        process(root);
        return maxSum;
    }

    // 方法一: 递归。 时间复杂度O(N)，空间复杂度O(N)
    //分两部分考虑  路径和肯定是以某个根节点出发的，可能是单侧链，也可能是左右子树都有。
    // 单侧链作为递归的返回以便上层分析；左右子树都有的情况下，左右子树的路径和都要算上，并作为maxSum的候选值
    public int process(TreeNode root) {
        if (root == null) return 0;
        //!!!!!!注意这里递归调用的是process，而不是maxPathSum
        int leftMax = Math.max(process(root.left), 0);
        int rightMax = Math.max(process(root.right), 0);
        maxSum = Math.max(maxSum, root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }

    //方法二：树形DP。 时间复杂度O(N)，空间复杂度O(N) ，更通用一点
    class ReturnData {
        int maxDistance; //根结点的树中最大路径和
        int maxDistanceFromRoot;   //从根结点到叶子节点的最大路径和

        public ReturnData(int maxDistance, int maxDistanceFromRoot) {
            this.maxDistance = maxDistance;
            this.maxDistanceFromRoot = maxDistanceFromRoot;
        }
    }

    public int maxPathSum1(TreeNode root) {
        return process1(root).maxDistance;
    }

    private ReturnData process1(TreeNode head) {
        if (head == null) return new ReturnData(Integer.MIN_VALUE, Integer.MIN_VALUE);

        ReturnData leftData = process1(head.left);
        ReturnData rightData = process1(head.right);

        int leftGain = Math.max(leftData.maxDistanceFromRoot, 0); //排除负数
        int rightGain = Math.max(rightData.maxDistanceFromRoot, 0); //排除负数

        //三种情况  head+left   head+right  head
        int maxSumFromRoot = Math.max(leftGain, rightGain) + head.val;
        //三种情况
        int maxDistance = Math.max(leftGain + rightGain + head.val, Math.max(leftData.maxDistance, rightData.maxDistance));
        return new ReturnData(maxDistance, maxSumFromRoot);
    }
}
