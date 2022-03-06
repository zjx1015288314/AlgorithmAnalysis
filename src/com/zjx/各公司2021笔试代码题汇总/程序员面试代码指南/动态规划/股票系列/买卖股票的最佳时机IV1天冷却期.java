package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划.股票系列;

/**
 * 在卖出股票之后，隔一天才能买入
 */
public class 买卖股票的最佳时机IV1天冷却期 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            if(i == 1) {
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            } else {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
        }
        return dp[n - 1][0];
    }
}
