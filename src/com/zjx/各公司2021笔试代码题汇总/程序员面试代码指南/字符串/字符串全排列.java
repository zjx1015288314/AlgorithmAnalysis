package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * 输入一个长度为 n 字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组。
 * 例如输入字符串ABC,则输出由字符A,B,C所能排列出来的所有字符串ABC,ACB,BAC,BCA,CBA和CAB。
 *
 * 思路:
 * 排列组合中用过的字符不能再用，所以要用 boolean visited[] 来标记哪一个用过，用过了就不能再组合
 * 题目说可能有重复字母，TreeSet 刚好存的值不能重复，所以用 TreeSet，假如用 List 存会存在：
 */
public class 字符串全排列 {

    private ArrayList<String> res = new ArrayList<>();
    private TreeSet<String> paths = new TreeSet<>();
    private StringBuilder path = new StringBuilder();
    private boolean[] visited;

    public ArrayList<String> Permutation(String str) {
        if (str == null || str.equals("")) {
            return res;
        }
        char[] strs = str.toCharArray();
        visited = new boolean[strs.length];
        permutationFromIndex(strs, 0);
        res.addAll(paths);
        return res;
    }

    /**
     * 时间复杂度：O(n×n!)，其中 n 为序列的长度。
     *
     * 算法的复杂度首先受 backtrack 的调用次数制约，backtrack 的调用次数为 ∑P(n,k) 次，
     * 其中 P(n,k)=(n−k)!/n!=n(n−1)…(n−k+1)，该式被称作 n 的 k - 排列，或者部分排列。
     * 而 ∑P(n,k)=n!+n!/1!+n!/2!+…+n!/(n−1)!<2n!+n!/2+n!/(2^2)+...+n!/(2^(n-2))<3n!
     * 这说明 backtrack 的调用次数是 O(n!) 的。
     *
     * 空间复杂度：O(n)，其中 n 为序列的长度。除答案数组以外，
     * 递归函数在递归过程中需要为每一层递归函数分配栈空间，所以这里需要额外的空间且该空间取决于递归的深度，
     * 这里可知递归调用深度为 O(n)。
     * 链接：https://leetcode.cn/problems/permutations/solutions/218275/quan-pai-lie-by-leetcode-solution-2/
     * @param strs
     * @param len
     */
    private void permutationFromIndex(char[] strs, int len) {
        if (len == strs.length) {
            paths.add(path.toString());
            return;
        }
        for (int i = 0; i < strs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                path.append(strs[i]);
                permutationFromIndex(strs, len + 1);
                //回溯 - 状态重置
                visited[i] = false;
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}
