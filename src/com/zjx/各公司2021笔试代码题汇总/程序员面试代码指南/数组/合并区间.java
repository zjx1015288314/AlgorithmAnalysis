package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 */
public class 合并区间 {

    /**
     * 自己的解法，本来以为要用Deque不断地弹出， 其实只和队尾元素比较并更新即可，因为在排序之后，
     * 后面的interval[]的左区间不会小于前面的左区间，最极端的情况也是左区间相等，所以不用一直弹出
     *
     * @param intervals
     * @return
     */
    public int[][] merge1(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));  //Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        Deque<int[]> deque = new LinkedList<>();

        for(int i = 0; i < intervals.length; i++) {
            if(deque.isEmpty() || intervals[i][0] > deque.peekLast()[1]) { //这里不用判断左区间，因为排序后左区间是递增的
                deque.addLast(intervals[i]);
            }else {
                deque.peekLast()[1] = Math.max(deque.peekLast()[1], intervals[i][1]);
            }
        }

        int[][] res = new int[deque.size()][];
        for(int i = 0; i < res.length; i++) {
            res[i] = deque.pollFirst();
        }
        return res;
    }

    /**
     * 官方解答：没有使用Deque，借用Arrays.copyOf(int[] arr, int len) 或者Arrays.copyOfRange(int[] arr, int from, int to)
     */
    public int[][] merge2(int[][] intervals) {
        if(intervals == null || intervals.length < 2) return intervals;
        int len = intervals.length;
        int[][] res = new int[len][2];  //也可以使用List<int[]>来接收,最后返回时使用List.toArray(new int[size][])
        int idx = -1;
        Arrays.sort(intervals,(o1,o2) -> o1[0] - o2[0]);
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res,idx + 1);
    }

}
