package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n
 * 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 *
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路: 递归
 */
public class 不同的二叉搜索树II {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new LinkedList<>();
        return generateTrees(1,n);
    }

    public List<TreeNode> generateTrees(int start, int end){
        List<TreeNode> result = new LinkedList<>();
        if(start > end){
            result.add(null); //!!!!!!!!!!!!!!!!!
            return result;
        }
        for(int i = start; i <= end; i++){
            List<TreeNode> leftTree = generateTrees(start,i-1);
            List<TreeNode> rightTree = generateTrees(i+1,end);
            for(TreeNode l : leftTree){
                for(TreeNode r : rightTree){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }
        return result;
    }

    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
     * 返回满足题意的二叉搜索树的种数。
     * 比上面的简单，只要求数量，则使用动态规划
     */
    public int numTrees(int n) {
        //由题：n>=1
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
