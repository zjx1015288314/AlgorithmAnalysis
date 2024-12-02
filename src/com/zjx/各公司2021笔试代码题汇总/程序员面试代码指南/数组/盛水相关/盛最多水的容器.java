package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.盛水相关;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 * 思路：不是最高的两个点盛的水最多，还得考虑距离因素  贪心策略  选最远最高的两个
 * https://leetcode.cn/problems/container-with-most-water/
 */
public class 盛最多水的容器 {
    public int maxArea(int[] height) {
        if(height == null || height.length == 1) {
            throw new RuntimeException("error");
        }
        int maxContainer = 0;
        int startIdx = 0;
        int endIdx = height.length - 1;
        while(startIdx < endIdx) {
            int curContainer = Math.min(height[startIdx], height[endIdx]) * (endIdx - startIdx);
            maxContainer = Math.max(maxContainer, curContainer);
            if(height[startIdx] < height[endIdx]) {
                // 这里如果endIdx往前移，无论如何也找不到比现在更大的容器了，因为宽度在减小，所以只能找更高的
                startIdx++;
            } else {
                endIdx--;
            }
        }
        return maxContainer;
    }
}
