package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划.股票系列;

/**
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以【尽可能地完成更多】的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 */
public class 买卖股票的最佳时机II {

    /**
     * 方法一：
     * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
     * 考虑例子[1,2,3,4,5]，数组的长度 n=5，由于对所有的1≤i<n都有 a[i]>a[i-1]，因此答案为
     * ans=∑(a[i]−a[i−1])=4
     * 但是实际的交易过程并不是进行4次买入和4次卖出，而是在第1天买入，第5天卖出。
     */
    public int maxProfit(int[] prices) {
        //贪心，有利润就买卖
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxProfit;
    }

    /**
     * 方法二：通用动态规划
     * dp[i][0]表示第i天交易完后手里没有股票的最大利润（i从0开始）
     * dp[i][1]表示到第i天交易完后手里持有一支股票的最大利润（i从0开始）
     */
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
