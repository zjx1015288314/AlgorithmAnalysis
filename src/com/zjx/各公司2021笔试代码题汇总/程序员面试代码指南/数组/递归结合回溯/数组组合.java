package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.递归结合回溯;

import java.util.*;

/**
 *  给定一个无重复元素的正整数数组candidates和一个正整数target，
 *  找出candidates中所有可以使数字和为目标数target的唯一组合。
 *  candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 *
 * 对于给定的输入，保证和为arget 的唯一组合数少于 150 个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class 数组组合 {
    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{1, 2}, 3);
        for(List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    /**
     * 方法一： 排序+递归+回溯
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();

        Arrays.sort(candidates);

        combinationSumHelper(candidates, target, 0, path, res);

        return res;
    }

    public static void combinationSumHelper(int[] candidates, int target, int idx, List<Integer> list, List<List<Integer>> res) {
        if(target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i = idx; i < candidates.length; i++){
            if (candidates[i] <= target) {
                list.add(candidates[i]);
                combinationSumHelper(candidates, target - candidates[i], i, list, res);
                list.remove(list.size() - 1);
            }else {
                break;
            }
        }
    }

    //方法二：每次都有选和不选两个选择
    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
