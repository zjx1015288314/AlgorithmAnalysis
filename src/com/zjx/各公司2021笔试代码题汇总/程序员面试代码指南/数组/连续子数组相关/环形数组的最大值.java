package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.连续子数组相关;

/**
 * 给定一个由整数数组 A表示的环形数组 C，求 C的非空子数组的最大可能和。
 *
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length时
 * C[i] = A[i]，且当i >= 0时C[i+A.length] = C[i]）
 *
 * 此外，子数组最多只能包含固定缓冲区 A中的每个元素一次。（形式上，对于子数组C[i], C[i+1],
 * ..., C[j]，不存在i <= k1, k2 <= j其中k1 % A.length= k2 % A.length）
 *
 * 示例 1：
 *
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 *
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
 */
public class 环形数组的最大值 {
    /**
     * 思路：子数组总有一段是连续的。
     * 1.当最大和的子数组并不同时包含头尾即一般情况时，正常求解;
     * 2.当最大和的子数组同时包含头尾时,反过来求最小和的子数组
     */
    public int maxSubarraySumCircular(int[] nums) {
        int sum = 0;  //数组总和
        int maxSumOfSubArr = nums[0];  //正常情况子数组的最大和
        int preMaxSum = 0;    //前缀最大和
        int minSumOfSubArr = 0;  //正常情况子数组的最小和,!!!!注意不是nums[0]/MAX_VALUE,因为该值可能不走循环导致sum-minSumOfSubArr异常
        int preMinSum = 0;    //前缀最小和

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            preMaxSum = preMaxSum <= 0 ? nums[i] : preMaxSum + nums[i];
            maxSumOfSubArr = Math.max(preMaxSum, maxSumOfSubArr);
        }

        for(int i = 1; i < nums.length - 1; i++) { //注意这里需要严格边界，否则{-3，-2，-1}可能求得最大值为0
            preMinSum = preMinSum >= 0 ? nums[i] : preMinSum + nums[i];
            minSumOfSubArr = Math.min(preMinSum, minSumOfSubArr);
        }
        return Math.max(maxSumOfSubArr, sum - minSumOfSubArr);
    }

    /**
     * 结果只有两种可能，一种包含数组首尾元素并由首尾两部分拼接而成，一种处于数组中间。
     * 先计算以0开头的子数组的元素和的最大值[0，i],再计算以len-1结尾的子数组的元素和的最大值（i,len-1]
     */
    public int maxSubarraySumCircular2(int[] A) {
        int len = A.length;
        int[] preMax = new int[len];
        int preSum = 0;
        for (int i = 0; i < len; i++){
            preSum += A[i];
            preMax[i] = i == 0 ? A[0] : Math.max(preMax[i-1],preSum);
        }

        //再计算以len-1结尾的子数组的元素和的最大值
        int[] postMax = new int[len];
        int postSum = 0;
        for (int i = len - 1; i >= 0; i--){
            postMax[i] = i == len - 1 ? 0 : Math.max(postMax[i+1],postSum);
            postSum += A[i];
        }

        int result = Integer.MIN_VALUE;
        //遍历前面创建的两个数组，找到包含首尾元素的最大子数组和
        for (int i = 0; i < len; i++)
            result = Math.max(preMax[i] + postMax[i], result);

        //遍历数组A，找到不包含首尾元素的最大子数组和
        int sum = 0;
        for (int i = 0; i < len; i++){
            sum = sum <= 0 ? A[i] : sum + A[i];
            result = Math.max(sum, result);
        }
        return result;
    }
}
