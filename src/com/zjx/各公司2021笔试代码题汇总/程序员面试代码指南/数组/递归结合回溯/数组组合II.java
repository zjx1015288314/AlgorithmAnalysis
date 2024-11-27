package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.递归结合回溯;

import java.util.*;

/**
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 *
 * 注意：解集不能包含重复的组合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 *
 * 参考@ref{数组组合}  思路是统计每个数字重复的个数，每次要用的时候减1使用，用完+1，同时在for循环内
 * 避开重复元素(i > 0 && candidates[i] == candidates[i - 1]),因为重复元素在前面已经使用过了
 */
public class 数组组合II {
    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum2(new int[]{1, 2}, 4);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(candidates);
        for (int i = 0; i < candidates.length; i++) {
            map.put(candidates[i], map.getOrDefault(candidates[i], 0) + 1);
        }

        combinationSumHelper(candidates, target, 0, path, map, res);

        return res;
    }

    public static void combinationSumHelper(int[] candidates, int target, int idx, List<Integer> path, Map<Integer,
            Integer> map, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // candidates[i]的次数在candidates[i - 1]用完了
        for (int i = idx; i < candidates.length; i++) {
            // !!! 这里应该是i > idx? 这段很重要，题目要求去重，如果不这样做会出现重复组合。
            // [2,5,2,1,2] ,target = 7, 结果为[[1,2,2],[5]]，而不是[[1,2,2],[1,2,2],[1,2,2][5]]
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] <= target) {
                int count = map.get(candidates[i]);
                if (count > 0) {
                    path.add(candidates[i]);
                    map.put(candidates[i], count - 1);
                    combinationSumHelper(candidates, target - candidates[i], i, path, map, res);
                    path.remove(path.size() - 1);
                    map.put(candidates[i], count);
                }
            } else {
                // 这里做了剪枝!!!
                break;
            }
        }


    }
}
