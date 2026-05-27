package com.zjx.各公司2021笔试代码题汇总.华为.面试;


/**
 * 2一桶球每次取一个或者三个 n个球有多少种取法
 * 4个球 1111  13  31三种取法
 * 5个球  11111 131 311
 */
public class Test9 {

    public static void main(String[] args) {
        System.out.println(bollNum(5));
    }

    private static int bollNum(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + (i >= 3 ? dp[i - 3] : 0);
        }
        return dp[n];
    }

}
