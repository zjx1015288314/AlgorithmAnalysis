package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划.股票系列;

/**
 * 买入的时候需要服务费， 解释：相当于买入股票的价格升高了。
 */
public class 买卖股票的最佳时机V服务费 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i-1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }
}
