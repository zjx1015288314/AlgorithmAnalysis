package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.背包;

import java.util.Scanner;

/**
 * 有 N 种物品和一个容量是 V 的背包。
 * 第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
 * 输出最大价值。
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 * 接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
 * 输出格式
 * 输出一个整数，表示最大价值。
 * 数据范围
 * 0<N,V≤100
 * 0<vi,wi,si≤100
 * 输入样例
 * 4 5
 * 1 2 3
 * 2 4 1
 * 3 4 3
 * 4 5 2
 * 输出样例：
 * 10
 */
public class MultiPackage1 {
    public static void main(String[] args) throws Exception {
        // 读入数据的代码
        Scanner reader = new Scanner(System.in);
        // 物品的数量为N
        int N = reader.nextInt();
        // 背包的容量为V
        int V = reader.nextInt();
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];
        int[] s = new int[N + 1];

        // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
        for (int i = 1; i <= N; i++) {
            v[i] = reader.nextInt();
            w[i] = reader.nextInt();
            s[i] = reader.nextInt();
        }
        reader.close();
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            //这里j逆序遍历的原因是dp[i][j]需要依赖dp[i-1][j-**]即左上角的状态，如果正序遍历的话，dp[i-1][j-**]状态会被覆盖
            for (int j = V; j >= v[j]; j--) {
                //当前体积j可以放下至少一块
                int num = Math.min(s[i], j / v[i]); //!!!这里是j/v[i]
                for (int k = 0; k < num + 1; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }
        System.out.print(dp[V]);
    }
}
