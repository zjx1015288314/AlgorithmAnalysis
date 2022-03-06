package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.背包;


import java.util.Scanner;

/**
 * 完全背包
 * 思路分析：
 * 注意这里当考虑放入一个物品 i 时应当考虑还可能继续放入 i，
 * 因此这里是dp[i][j-weight[i]]+value[i], 而不是dp[i-1][j-weight[i]]+value[i]。
 * 放第i件物品。这里的处理和01背包有所不同，因为01背包的每个物品只能选择一个
 * 因此选择放第i件物品就意味着必须转移到dp[i-1][v-w[i]]这个状态；但是完全背包
 * 问题不同，完全背包如果选择放第i件物品之后并不是转移到dp[i-1][v-w[i]]这个状态，
 * 而是转移到dp[i][v-w[i]]，这是因为每种物品可以放任意件（注意有容量的限制，因此
 * 还是有限的），放了第i件物品后还可以继续放第i件物品，直到第二维的v-w[i]无法保
 * 持大于等于0为止
 **/
public class WholePackage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int V = sc.nextInt();
        int[] v = new int[N + 1];
        int[] w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                //二维数组
                //if(v[i] > j)
                //    dp[i][j] = dp[i-1][j];
                //else
                //    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-weight[i-1]]+value[i-1]);
                //优化后
                if (j >= v[i]) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
                }
            }
        }
        System.out.print(dp[V]);
    }
}
