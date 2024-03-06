package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

/**
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 求根节点到叶节点数字之和 {
    /**
     * 原来的函数基础上递归   tmp = tmp / 10;回溯不要忘记
     */
    int sum = 0;
    int tmp = 0;

    public int sumNumbers1(TreeNode root) {
        if (root == null) return 0;
        tmp = tmp * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += tmp;
        }
        sumNumbers1(root.left);
        sumNumbers1(root.right);
        tmp = tmp / 10;  //回溯
        return sum;
    }

    /**
     * 自定义递归函数sumNumbersHelper，比较常见
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        //已经累计的和
        int curSum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return curSum;
        } else {
            return dfs(root.left, curSum) + dfs(root.right, curSum);
        }
    }
}
