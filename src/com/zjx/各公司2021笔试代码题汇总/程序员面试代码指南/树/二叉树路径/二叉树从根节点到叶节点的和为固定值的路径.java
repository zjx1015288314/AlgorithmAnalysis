package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.二叉树路径;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * 如果题目要求是从根节点到任意节点的路径和
 * root.left == null && root.right == null可以省略不要
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树从根节点到叶节点的和为固定值的路径 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum, 0, new ArrayList<>());
        return res;
    }

    public void dfs(TreeNode root, int sum, int preSum, ArrayList<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        preSum += root.val;
        dfs(root.left, sum, preSum, list);
        dfs(root.right, sum, preSum, list);
        //！！！！如果题目要求是从根节点到任意节点的路径和  这里的root.left == null && root.right == null可以省略不要
        if (root.left == null && root.right == null && preSum == sum) {
            res.add(new ArrayList<>(list));
        }
        list.remove(list.size() - 1);
    }
}
