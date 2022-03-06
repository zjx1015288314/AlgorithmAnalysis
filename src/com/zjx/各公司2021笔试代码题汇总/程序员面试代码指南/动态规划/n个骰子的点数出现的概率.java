package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 *
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,
 *       0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：先把n个骰子出现点数的次数统计一遍，由减治的想法可得需要先算出n-1个骰子的情况,所以
 * 选择动态规划存储中间结果
 */
public class n个骰子的点数出现的概率 {
    public double[] dicesProbability(int n) {
        int faces = 6;
        int pointNum = n * faces;
        int[][] dp = new int[n + 1][pointNum + 1];

        //初始情况记得处理,不然都是0
        for(int j = 1; j <= faces; j++) {
            dp[1][j] = 1;
        }

        for(int i = 2; i <= n; i++) {
            for(int j = i; j <= faces * i; j++) {
                for(int k = 1; k <= j && k <= faces; k++) { //k <= j注意
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        double sum = Math.pow(6, n);
        double[] res = new double[pointNum - n + 1];
        for(int i = n; i<= pointNum; i++) {
            res[i - n] = dp[n][i] / sum;
        }
        return res;
    }
}
