package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.背包;

import java.util.Scanner;

/**
 * 有 N 件物品和一个容量是 V 的背包，背包能承受的最大重量是 M。
 * 每件物品只能用一次。体积是 vi，重量是 mi，价值是 wi。
 * 求解将哪些物品装入背包，可使物品总体积不超过背包容量，总重量不超过背包可承受的最大重量，且价值总和最大。
 * 输出最大价值。
 * 输入格式
 * 第一行两个整数，N，V,M，用空格隔开，分别表示物品件数、背包容积和背包可承受的最大重量。
 * 接下来有 N 行，每行三个整数 vi,mi,wi，用空格隔开，分别表示第 i 件物品的体积、重量和价值。
 * 输出格式
 * 输出一个整数，表示最大价值。
 * 数据范围
 * 0<N≤1000
 * 0<V,M≤100
 * 0<vi,mi≤100
 * 0<wi≤1000
 * 输入样例
 * 4 5 6
 * 1 2 3
 * 2 4 4
 * 3 4 5
 * 4 5 6
 * 输出样例：
 * 8
 */
public class TwoDimensionalPackage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int V = sc.nextInt();
        int M = sc.nextInt();

        int[] v = new int[N + 1]; //体积
        int[] m = new int[N + 1]; //质量
        int[] w = new int[N + 1]; //价值
        for (int i = 1; i <= N; i++) {  //注意这里i的取值不是0-N-1,否则会出错
            v[i] = sc.nextInt();
            m[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        //比01背包多了一个维度
        int[][] dp = new int[V + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                for (int k = M; k >= m[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - v[i]][k - m[i]] + w[i]);
                }
            }
        }
        System.out.print(dp[V][M]);
    }
}
