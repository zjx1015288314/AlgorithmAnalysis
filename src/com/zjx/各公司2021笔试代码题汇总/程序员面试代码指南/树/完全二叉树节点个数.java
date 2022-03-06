package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

/**
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 *
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * 示例 2：
 * 输入：root = []
 * 输出：0
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 */
public class 完全二叉树节点个数 {

    /**
     * 每次都分左右两个子树去计算，总有一颗子树为满二叉树， 假如根节点在第一层，则满二叉树节点数为(2^n-1)
     *
     * 时间复杂度O((logn)^2)   空间O(1)
     */
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);  //1<<left为2^n，把根节点都算进去了
        }else{
            return countNodes(root.left) + (1<<right);
        }
    }

    public int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }
}
