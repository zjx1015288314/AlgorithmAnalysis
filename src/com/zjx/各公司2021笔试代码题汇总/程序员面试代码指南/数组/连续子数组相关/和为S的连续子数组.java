package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.连续子数组相关;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * @ref{数组中连续子数组和的最大值}有点类似
 *
 * 思路：前缀和
 */
public class 和为S的连续子数组 {
    public int subarraySum(int[] nums, int k) {
        //思路：连续的子数组肯定是由两个前缀子数组相减得到的
        Map<Integer, Integer> map = new HashMap<>();
        int preSum = 0;
        int count = 0;
        map.put(0, 1); //考虑连续子数组从头开始的情况，如果不加的话，会漏掉
        for(int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if(map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
