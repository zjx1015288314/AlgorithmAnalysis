package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树的最小深度_简单 {
    /**
     * 最简单递归
     */
    public int minDepth1(TreeNode root) {
        if(root == null) return 0;
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        //1.如果左孩子和右孩子有为空的情况，直接返回m1+m2+1
        //2.如果都不为空，返回较小深度+1
        return (root.left == null || root.right == null) ? m1 + m2 + 1 : Math.min(m1,m2) + 1;
    }

    /**
     * BFS 迭代   找到第一个叶子节点就返回
     */
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            depth++;
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                //最重要的一步  当BFS遍历遇到第一个叶子节点时立即返回，
                //每层的深度在for循环开始前已经确定(遍历过的都是非叶子节点)
                if(node.left == null && node.right == null) return depth;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        return depth;
    }

    /**
     * DFS  (类)先序遍历版本   时间复杂度最坏情况是在O(N),空间复杂度最坏也为O(N)
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        stack.add(new Pair(root, 1));
        int min_depth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pop();
            TreeNode currentNode = current.getKey();
            int currentDepth = current.getValue();
            if(currentDepth > min_depth) continue;   //不去遍历孩子节点
            if ((currentNode.left == null) && (currentNode.right == null)) {
                min_depth = Math.min(min_depth, currentDepth);
            }
            if (currentNode.left != null) {
                stack.push(new Pair(currentNode.left, currentDepth + 1));
            }
            if (currentNode.right != null) {
                stack.push(new Pair(currentNode.right, currentDepth + 1));
            }
        }
        return min_depth;
    }
}
