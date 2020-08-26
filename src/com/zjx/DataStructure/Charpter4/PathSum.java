package com.zjx.DataStructure.Charpter4;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
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
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 */
public class PathSum {

    /**
     * 时间复杂度:O(N),空间复杂度：最坏情况即退化为链表，为O(N);如果是平衡树，则为O(logN)
     * 思路：刚开始想的是通过先序遍历和ArrayList保留每条通向叶子节点的路径上的所有节点，这样会造成空间浪费，因为每条路径有
     * 很多节点是重复的，为了降低空间复杂度，用一个全局数组tmpList保留每条路径上节点(因为这些路径是从左向右一次遍历的，所以
     * 不会发生冲突);每当从子节点返回到父节点之前从tmpList中删除该子节点，这样就保证了每条路径的唯一性
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> tmpList = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        helper(root, sum);
        return result;
    }

    public void helper(TreeNode root, int sum) {
        if (root == null) return;
        tmpList.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            result.add(new ArrayList<>(tmpList));  //这里必须要new一个新链表,如果单单将tmpList加入,则result中各个元素指向同一个List!!!
        }
        helper(root.left, sum - root.val);
        helper(root.right, sum - root.val);
        tmpList.remove(tmpList.size() - 1);  //最关键的一步
    }

    public static void main(String[] args) {
        System.out.println("二分,回溯,递归,分治".replaceAll("([,;\\s]+)", "---$1---"));
        String s = "肚...子。。好饿........，....早知道.....当.....初...。。。多.....刷.....点。。。力.....扣了.........!";
        String str = s.replaceAll("[.。]+", "");
        System.out.println(str);
        new PathSum().reverseStr("11111111111111111111111111111111111111111111111111111111111111111111111111", 4);
    }

    public String reverseStr(String s, int k) {
        if (s.length() < k) {
            return new StringBuffer(s).reverse().toString();
        } else if (s.length() <= 2 * k) {
            String tmp1 = s.substring(0, k + 1);
            String tmp2 = s.substring(k + 1);
            StringBuffer sb = new StringBuffer(tmp1).reverse();
            sb.append(tmp2);
            return sb.toString();
        }
        String tmp1 = s.substring(0, 2 * k + 1);
        String tmp2 = s.substring(2 * k + 1);
        tmp1 = reverseStr(tmp1, k);
        tmp2 = reverseStr(tmp2, k);
        return tmp1 + tmp2;
    }
}
