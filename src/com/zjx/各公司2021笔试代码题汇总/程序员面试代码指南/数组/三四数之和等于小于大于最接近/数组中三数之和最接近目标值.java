package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.三四数之和等于小于大于最接近;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 */
public class 数组中三数之和最接近目标值 {

    //排序+双指针
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length <= 2) return 0;
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) { return target;}

                if(Math.abs(sum - target) < Math.abs(res - target)){
                    res = sum;
                }

                if(sum < target) {
                    j++;
                    while(j < k && nums[j] == nums[j - 1]) j++;
                } else {
                    k--;
                    while(j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        return res;
    }
}
