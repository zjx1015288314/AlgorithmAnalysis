package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划.股票系列;

/**
 * 给定一个数组 prices，它的第i个元素prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票,只允许买卖一次。
 * 设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 买卖股票的最佳时机 {
    /**
     * 方法一：通用动态规划
     * dp[i][0]表示第i天交易完后手里没有股票的最大利润（i从0开始）
     * dp[i][1]表示到第i天交易完后手里持有一支股票的最大利润（i从0开始）
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);//注意这里不是dp[i - 1][0]-prices[i]
        }
        return dp[n - 1][0];
    }

    /**
     * 优化空间
     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];

        for(int i = 1; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    /**
     * 维护price[i]之前遇到的最小值,每次都试图计算并更新最大利润
     */
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;

        int minPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price > minPrice) {
                maxProfit = Math.max(maxProfit, price - minPrice);
            } else {
                minPrice = price;
            }
        }
        return maxProfit;
    }
}
