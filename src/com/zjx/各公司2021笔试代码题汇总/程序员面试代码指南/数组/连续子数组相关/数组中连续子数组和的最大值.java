package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.连续子数组相关;

/**
 * 输入一个长度为n的整型数组a，数组中的一个或连续多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为 O(n).
 *
 * 提示:
 * 1 <= n <= 500
 * -100 <= a[i] <= 100
 *
 * @link https://www.nowcoder.com/practice/459bd355da1549fa8a49e350bf3df484?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 数组中连续子数组和的最大值 {
    public int FindGreatestSumOfSubArray(int[] array) {
        int maxSumOfSubArr = Integer.MIN_VALUE;
        int preMaxSum = 0;
        for(int i = 0; i < array.length; i++) {
            //以下是自己的答案
//            preMaxSum = i == 0 ? array[i] : preMaxSum + array[i];
//            preMaxSum = Math.max(preMaxSum, array[i]);
            //官方题解
            preMaxSum = preMaxSum <= 0 ? array[i] : preMaxSum + array[i];

            maxSumOfSubArr = Math.max(preMaxSum, maxSumOfSubArr);
        }
        return maxSumOfSubArr;
    }
}
