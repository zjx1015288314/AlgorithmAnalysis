package com.zjx.各公司2021笔试代码题汇总.华为.面试;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [start(i), end(i)] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Test7 {

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = merge(intervals);
        for (int i = 0; i < res.length; i++) {
            int[] arr = res[i];
            Arrays.stream(arr).forEach(System.out::print);

        }
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[][] res = new int[intervals.length][2];
        int resIdx = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (i == 0) {
                res[resIdx] = intervals[i];
                continue;
            }

            int leftEnd = res[resIdx][1];
            if (intervals[i][0] > leftEnd) {
                res[++resIdx] = intervals[i];
            } else {
                res[resIdx][1] = Math.max(leftEnd, intervals[i][1]);
            }
        }
        return Arrays.copyOfRange(res, 0, resIdx + 1);
    }


}
