package com.zjx.各公司2021笔试代码题汇总.华为.面试;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定随机数组 data[]，寻找指定k个数的和为m的所有组合
 *
 * == 示例 ==
 *
 * 输入
 * int data[] = {1,2,3,4,5,6,8,10};
 * int k = 2;
 * int m = 7;
 * 返回
 * {{1, 6}, {2, 5}, {3, 4}}
 */
public class Test4 {

    private static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        findSumWithK(new int[]{1,2,3,4,5,6,8,10}, 2, 7);
        res.forEach(System.out::println);
    }

    private static void findSumWithK(int[] data, int k, int m) {
        if (data == null || data.length == 0 || k <= 0 || m <= 0) {
            return;
        }
        if (k <= data.length) {
            int[] path = new int[k];
            dfs(data, k, m, path, 0, 0);
        }
    }

    private static void dfs(int[] data, int k, int m, int[] path, int start, int sum) {
        if (k == 0) {
            if (sum == m) {
                List<Integer> list = new ArrayList<>();
                for (int j : path) {
                    list.add(j);
                }
                res.add(list);
            }
            return;
        }
        for (int i = start; i < data.length; i++) {
            path[path.length - k] = data[i];
            dfs(data, k - 1, m, path, i + 1, sum + data[i]);
        }
    }


    /**
     * 更标准的写法
     */
    private static List<List<Integer>> findSumWithK1(int[] data, int k, int m) {
        List<List<Integer>> res = new ArrayList<>();
        if (data == null || data.length == 0 || k <= 0) {
            return res;
        }

        dfs(data, k, m, 0, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(int[] data,
                            int k,
                            int target,
                            int start,
                            List<Integer> path,
                            List<List<Integer>> res) {

        // 终止条件
        if (path.size() == k) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = start; i < data.length; i++) {
            // 剪枝（可选）
            if (data[i] > target) continue;
            path.add(data[i]);
            dfs(data, k, target - data[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

}
