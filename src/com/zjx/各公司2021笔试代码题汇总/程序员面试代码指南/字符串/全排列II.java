package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 思路:
 * 排列组合中用过的字符不能再用，所以要用 boolean visited[] 来标记哪一个用过，用过了就不能再组合
 * 题目说可能有重复字母。这类题型统一先排序，保证相同的字符只使用固定的顺序，
 * @see 字符串全排列 的升级
 * 链接： https://leetcode.cn/problems/permutations-ii/
 */
public class 全排列II {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        doPermute(nums, visited, new ArrayList<>());
        return res;
    }


    private void doPermute(int[] nums, boolean[] visited, List<Integer> path) {
        if (path.size() >= nums.length) {
            res.add(new ArrayList(path));
        }

        for (int i = 0; i < nums.length; i++) {
            //!!!! if 的第二个判断条件非常重要
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);
            doPermute(nums, visited, path);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
