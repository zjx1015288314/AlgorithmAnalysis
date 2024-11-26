package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.盛水相关;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 * https://leetcode.cn/problems/trapping-rain-water-ii/description/
 * 提示:
 *   m == heightMap.length
 *   n == heightMap[i].length
 *   1 <= m, n <= 200
 *   0 <= heightMap[i][j] <= 2 * 104
 *
 * 思路: 参考接雨水1的双指针作法，由外向内，找到最小的柱子（作为短板）, 算出该木板周围的柱子的水量。
 * 期间维护一个最小堆, 每次都把最短板取出来计算周围水量。最后即可得到总接水量
 */
public class 接雨水II {

    public int trapRainWater(int[][] heightMap) {
        int[][] directs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int ans = 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{heightMap[i][j], i, j});
                    //!!! -1表示计算过，之后不需要再看。和回溯算法中的作用有点像
                    heightMap[i][j] = -1;
                }
            }
        }

        while (!pq.isEmpty()) {
            int[] minHeightMap = pq.poll();
            int minHeight = minHeightMap[0];
            int i = minHeightMap[1];
            int j = minHeightMap[2];
            for (int[] direct: directs) {
                int x = i + direct[0], y = j + direct[1];
                //!!!!
                if (x >= 0 && x < m && y >= 0 && y < n && heightMap[x][y] >= 0) {
                    ans += Math.max(minHeight - heightMap[x][y], 0);
                    //!!! 这里放入的是接雨水后的高度，不是原柱子高度，也即填入‘水泥’
                    pq.offer(new int[]{Math.max(minHeight,heightMap[x][y]), x, y});
                    heightMap[x][y] = -1;
                }
            }
        }
        return ans;
    }


}
