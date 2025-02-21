package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

import java.util.*;

/**
 * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 *
 * 数据范围：树上节点数满足
 * 1≤n≤10^5, 节点值val满足区间 [0,n)
 * 要求：时间复杂度O(n)
 * 注：本题保证二叉树中每个节点的val值均不相同。
 * 链接：https://www.nowcoder.com/practice/e0cc33a83afe4530bcec46eba3325116?tpId=295&tqId=1024325
 */
public class 二叉树找到两个节点的最近公共祖先 {

    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
//        return helper(root, o1, o2).val;
        return helper2(root, o1, o2);
    }

    /**
     * 方法一 递归查找
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    private TreeNode helper (TreeNode root, int o1, int o2) {
        if (root == null || root.val == o1 || root.val == o2) {
            return root;
        }
        TreeNode left = helper(root.left, o1, o2);
        TreeNode right = helper(root.right, o1, o2);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 方法二 迭代
     * 通过层序遍历记录子节点和父节点的映射关系。存储o1及其所有父节点，对比o2及其父节点，找到最近的公共祖先。
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    public int helper2 (TreeNode root, int o1, int o2) {
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        parent.put(root.val, Integer.MIN_VALUE);//根节点没有父节点，给他默认一个值;该步骤必不可少
        queue.add(root);
        //直到两个节点都找到为止。
        while (!parent.containsKey(o1) || !parent.containsKey(o2)) {
            //队列是一边进一边出，这里poll方法是出队，
            TreeNode node = queue.poll();
            if (node.left != null) {
                //左子节点不为空，记录下他的父节点
                parent.put(node.left.val, node.val);
                //左子节点不为空，把它加入到队列中
                queue.add(node.left);
            }
            //右节点同上
            if (node.right != null) {
                parent.put(node.right.val, node.val);
                queue.add(node.right);
            }
        }
        Set<Integer> ancestors = new HashSet<>();
        //记录下o1和他的祖先节点，从o1节点开始一直到根节点。
        while (parent.containsKey(o1)) {
            ancestors.add(o1);
            o1 = parent.get(o1);
        }
        //查看o1和他的祖先节点是否包含o2节点，如果不包含再看是否包含o2的父节点……
        while (!ancestors.contains(o2)) {
            o2 = parent.get(o2);
        }
        return o2;
    }

}
