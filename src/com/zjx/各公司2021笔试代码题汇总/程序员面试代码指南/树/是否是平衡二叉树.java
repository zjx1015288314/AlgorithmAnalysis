package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

/**
 * 给定一个二叉树，判断它是否是
 * 平衡二叉树
 *
 * https://leetcode.cn/problems/balanced-binary-tree/
 */
public class 是否是平衡二叉树 {
    class ReturnData{
        int maxSize;
        boolean isBanlanced;
        public ReturnData(int maxSize, boolean flag){
            this.maxSize = maxSize;
            this.isBanlanced = flag;
        }
    }
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null) return true;
        return helper(root).isBanlanced;
    }

    private ReturnData helper(TreeNode root){
        if(root == null) return new ReturnData(0,true);

        ReturnData leftData = helper(root.left);
        ReturnData rightData = helper(root.right);
        boolean isBanlanced = leftData.isBanlanced && rightData.isBanlanced &&
                Math.abs(leftData.maxSize - rightData.maxSize) <= 1;
        int maxSize = Math.max(leftData.maxSize,rightData.maxSize) + 1;
        return new ReturnData(maxSize,isBanlanced);
    }
}
