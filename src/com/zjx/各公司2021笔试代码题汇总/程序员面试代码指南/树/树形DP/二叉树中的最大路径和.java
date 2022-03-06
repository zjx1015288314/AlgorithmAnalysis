package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.树形DP;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树中的最大路径和 {

    class ReturnData {
        int maxDistance; //根结点的树中最大路径和
        int maxSum;   //从根结点到叶子节点的最大路径和

        public ReturnData(int maxDistance, int maxSum) {
            this.maxDistance = maxDistance;
            this.maxSum = maxSum;
        }
    }

    //树形DP
    public int maxPathSum(TreeNode root) {
        return process(root).maxDistance;
    }

    private ReturnData process(TreeNode head) {
        if (head == null) return new ReturnData(Integer.MIN_VALUE, Integer.MIN_VALUE);

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        int left_gain = Math.max(leftData.maxSum, 0); //排除负数
        int right_gain = Math.max(rightData.maxSum, 0); //排除负数

        //三种情况  head+left   head+right  head
        int maxSum = Math.max(left_gain, right_gain) + head.val;
        //int maxSum = left_gain + right_gain + head.val;
        //上面注释掉的行为错误行，maxSum只能包含从根节点开始到某一子树的路径和，而不是两条子树都包括

        //三种情况
        int maxDistance = Math.max(left_gain + right_gain + head.val, Math.max(leftData.maxDistance, rightData.maxDistance));
        return new ReturnData(maxDistance, maxSum);
    }
}
