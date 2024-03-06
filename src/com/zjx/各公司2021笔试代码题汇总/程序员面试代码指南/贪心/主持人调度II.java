package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有 n 个活动即将举办，每个活动都有开始时间与活动的结束时间，第 i 个活动的开始时间是 starti ,
 * 第 i 个活动的结束时间是 endi ,举办某个活动就需要为该活动准备一个活动主持人。
 * 一位活动主持人在同一时间只能参与一个活动。并且活动主持人需要全程参与活动，换句话说，
 * 一个主持人参与了第 i 个活动，那么该主持人在 (starti,endi) 这个时间段不能参与其他任何活动。
 * 求为了成功举办这 n 个活动，最少需要多少名主持人。
 *
 * 数据范围: 1≤n≤10^5，−2^32 ≤ starti ≤endi ≤ 2^31−1
 * 复杂度要求：时间复杂度O(nlogn) ，空间复杂度O(n)
 *
 * https://www.nowcoder.com/practice/4edf6e6d01554870a12f218c94e8a299?tpId=295&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj
 */
public class 主持人调度II {

    /**
     * step 1: 利用辅助数组获取单独各个活动开始的时间和结束时间，然后分别开始时间和结束时间进行排序，方便后面判断是否相交。
     * step 2: 遍历n个活动，如果某个活动开始的时间大于之前活动结束的时候，当前主持人就够了，活动结束时间往后一个。
     * step 3: 若是出现之前活动结束时间晚于当前活动开始时间的，则需要增加主持人。
     * @param n
     * @param startEnd
     * @return
     */
    public int minmumNumberOfHost (int n, int[][] startEnd) {
        // write code here]
        int[] start = new int[startEnd.length];
        int[] end = new int[startEnd.length];

        for (int i = 0; i < startEnd.length; i++) {
            start[i] = startEnd[i][0];
            end[i] = startEnd[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int res = 0; // res初始化为0即可, 因为第一个活动的开始时间肯定小于结束时间
        int j = 0;
        for (int k : start) {
            if (k >= end[j]) { // 如果当前活动的开始时间大于等于之前活动的结束时间，那么当前主持人就够了, ！！！此时j不变化
                j++;
            } else {
                res++;
            }
        }
        return res;
    }

    /**
     * 具体做法：
     *
     * step 1：重载sort函数，将开始时间早的活动放在前面，相同情况下再考虑结束时间较早的。
     * step 2：使用小顶堆辅助，其中堆顶是还未结束的将要最快结束的活动的结束时间。
     * step 3：首先将int的最小数加入堆中，遍历每一个开始时间，若是堆顶的结束时间小于当前开始时间，可以将其弹出，说明少需要一个主持人。
     * step 4：除此之外，每次都需要将当前的结束时间需要加入堆中，代表需要一个主持人，最后遍历完成，堆中还有多少元素，就需要多少主持人。
     */
    public int minmumNumberOfHost1 (int n, int[][] startEnd) {
        //按列排序，按开始时间递增排
        Arrays.sort(startEnd, Comparator.comparing(o -> o[0]));
        //小顶堆
        PriorityQueue<Integer> q = new PriorityQueue<>();
        //可能有负区间
        q.offer(Integer.MIN_VALUE);
        for(int i = 0; i < n; i++){
            //最近的活动结束时间小于当前活动开始时间
            if(q.peek() <= startEnd[i][0])
                q.poll();
            q.offer(startEnd[i][1]);
        }
        //剩余的活动等于主持人数
        return q.size();
    }

}
