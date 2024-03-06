package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.背包;

import java.util.Arrays;

/**
 * 给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
 * 如果无解，请返回-1.
 * 数据范围：数组大小满足0≤n≤10000 ， 数组中每个数字都满足0<val≤10000，0≤aim≤5000
 * 要求：时间复杂度O(n×aim) ，空间复杂度O(aim)。
 * https://www.nowcoder.com/practice/3911a20b3f8743058214ceaa099eeb45?tpId=295&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj
 */
public class 找零钱 {

    public int minMoney (int[] arr, int aim) {
        // write code here
        int[] dp = new int[aim + 1];
        //dp[i]表示凑齐i元最少需要多少货币数
        Arrays.fill(dp, aim + 1);
        dp[0] = 0;
        //遍历1-aim元
        for (int i = 1; i <= aim; i++) {
            //每种面值的货币都要枚举
            for (int j = 0; j < arr.length; j++) {
                //如果面值不超过要凑的钱才能用
                if (arr[j] <= i)
                    //维护最小值
                    dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
            }
        }
        //如果最终答案大于aim代表无解
        return dp[aim] > aim ? -1 : dp[aim];
    }

}
