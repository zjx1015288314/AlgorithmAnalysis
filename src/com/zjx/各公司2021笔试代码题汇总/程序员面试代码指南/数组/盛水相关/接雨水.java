package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.盛水相关;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，
 * 下雨之后能接多少雨水。
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 */
public class 接雨水 {
    /**
     * 方法一：遍历两遍数组，拿到height[i]左边看过去最高的高度和右边看过去最高的高度
     * 不管方法一还是二，计算MaxHeight时都要把自己考虑进去，否则会出现和事实不符的逻辑
     * 导致本来没有的情况，也计算出雨水，且为负
     */
    public int trap1(int[] height) {
        int n = height.length;
        int[] leftMaxArr = new int[n];
        int[] rightMaxArr = new int[n];
        int leftMax = 0;
        int rightMax = 0;
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, height[i]);
            leftMaxArr[i] = leftMax;
        }
        for (int i = n - 1; i >= 0; i--) {
            rightMax = Math.max(rightMax, height[i]);
            rightMaxArr[i] = rightMax;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (Math.min(leftMaxArr[i], rightMaxArr[i]) - height[i]);
        }
        return sum;
    }

    /**
     * 方法一的优化，一边计算一边统计
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int ans = 0;
         int left = 0, right = height.length - 1;
         int leftMax = 0, rightMax = 0;
         while (left < right) {
             //注意，leftMax和rightMax要在判断之前统计
             leftMax = Math.max(leftMax, height[left]);
             rightMax = Math.max(rightMax, height[right]);
             if (leftMax < rightMax) {
                 ans += leftMax - height[left];
                 ++left;
             } else {
                 ans += rightMax - height[right];
                 --right;
             }
         }
         return ans;
    }

    /**
     * 单调栈：只保留一个索引栈 2.不管什么时候，height[i]都会进栈，所以for循环开头
     * 不用写heightStack.isEmpty() || heightStack.peek() > height[i]
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int sumWater = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < height.length; ++i) {
            // height[i] > height[stack.peek()]得到一个可以接雨水的区域
            int midHeight = 0;   //表示两个柱子之间的区域的高度，相邻的两个柱子之间为0
            //考虑等于的情况，官方题解没有考虑，而是直接存进单调栈，我的单调栈是真正的单调递减栈
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int left = stack.pop();  // 获得接雨水位置的左边界
                int currWidth = i - left - 1;  // 获取接雨水区域的宽度
                int currHeight = height[left] - midHeight;
                sumWater += currWidth * currHeight;
                midHeight = height[left];
            }
            //上面解决了所有比height[i]小的情况，现在把栈顶与height[i]之间的雨水列出来
            if (!stack.isEmpty()) {
                sumWater += (height[i] - midHeight) * (i - stack.peek() - 1);
            }
            stack.push(i); // 在对下标i处计算能接的雨水量之后,将i入栈,继续遍历后面的下标
        }
        return sumWater;
    }
}
