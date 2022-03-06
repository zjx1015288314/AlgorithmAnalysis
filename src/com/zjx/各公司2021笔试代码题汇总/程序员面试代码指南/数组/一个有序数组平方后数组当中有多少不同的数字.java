package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

import static java.lang.Math.abs;

/**
 * 一个有序数组，有重复的数，平方后，数组当中有多少不同的数字
 * 该题和递增递减数组求不重复的数字思路一样，都是从两边开始向中间查找
 * @author zhaojiexiong
 * @create 2020/7/21
 * @since 1.0.0
 */
public class 一个有序数组平方后数组当中有多少不同的数字 {
    public static void main(String[] args) {
        int[] num = {-1,-1,1,2,2,3,4,4};
        int[] num1 = {-1};
        System.out.println(findDiff(num));
        System.out.println(findDiff(num1));
    }
    public static int findDiff(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int res = 0;
        int left = 0;
        int right = nums.length - 1;
        long pre = Integer.MIN_VALUE - 1;

        while (left <= right) {
            if (abs(nums[left]) > abs(nums[right])) {
                if (pre != abs(nums[left])){
                    res++;
                    pre = abs(nums[left]);
                }
                left++;
            } else {
                if (pre != abs(nums[right])) {
                    res++;
                    pre = abs(nums[right]);
                }
                right--;
            }
        }
        return res;
    }
}
