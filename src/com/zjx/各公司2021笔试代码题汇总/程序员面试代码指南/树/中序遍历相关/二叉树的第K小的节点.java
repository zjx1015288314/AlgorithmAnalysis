package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.中序遍历相关;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

/**
 * 给定一棵结点数为 n 二叉搜索树，请找出其中的第 k 小的TreeNode结点。
 *
 * 数据范围： n <= 100, 0 ≤ k ≤ 100，树上每个结点的值满足0 ≤ val ≤ 100
 * 要求：空间复杂度 O(1)，时间复杂度O(n)
 * 注意：不是返回结点的值
 *
 * @link https://www.nowcoder.com/practice/ef068f602dde4d28aab2b210e859150a?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 * 思路：莫里斯遍历
 */
public class 二叉树的第K小的节点 {
    TreeNode KthNode(TreeNode pRoot, int k) {
        if(pRoot == null || k == 0) return null;

        TreeNode cur = pRoot;
        while(cur != null) {
            if(cur.left == null) {
                if(--k == 0) {
                    return cur;
                }
                cur = cur.right;
            }else {
                TreeNode pre = cur.left;
                while(pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if(pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                if(pre.right == cur) {
                    pre.right = null;
                    if(--k == 0) {
                        return cur;
                    }
                    cur = cur.right;
                }
            }
        }
        return null;
    }
}
