package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.背包;

import java.util.Scanner;

/**
 * 混合背包
 * 有 N 种物品和一个容量是 V 的背包。物品一共有三类：
 * 第一类物品只能用1次（01背包）；
 * 第二类物品可以用无限次（完全背包）；
 * 第三类物品最多只能用 si 次（多重背包）；
 * 每种体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
 * 输出最大价值。
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 * 接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
 * si=−1 表示第 i 种物品只能用1次；
 * si=0 表示第 i 种物品可以用无限次；
 * si>0 表示第 i 种物品可以使用 si 次；
 * 输出一个整数，表示最大价值。
 * 0<N,V≤1000
 * 0<vi,wi≤1000
 * −1≤si≤1000
 * 输入样例
 * 4 5
 * 1 2 -1
 * 2 4 1
 * 3 4 0
 * 4 5 2
 * 输出样例：
 * 8
 */
public class MixPackage {
    public static void main(String[] args) {
        // 读入数据的代码
        Scanner reader = new Scanner(System.in);
        // 物品的数量为N
        int N = reader.nextInt();
        // 背包的容量为V
        int V = reader.nextInt();

        int[] dp = new int[N + 1];



        //按照每行处理
        for (int i = 1; i <= N; i++) {
            int v = reader.nextInt();
            int w = reader.nextInt();
            int s = reader.nextInt();
            if (s == 0){
                //完全背包
                for (int j = v; j <= V; j++) {
                    dp[j] = Math.max(dp[j],dp[j - v] + w);
                }
            }else{
                //多重背包问题，01背包是多重背包的特例，可以一并处理
                s = Math.abs(s);
                //这里是MultiPackage2中优化的步骤，不过是把j * v存储为一个Goods对象的体积
                for (int j = 1; j <= s; j *= 2) {
                    s -= j;
                    for (int k = V; k >= j * v; k--) { //01背包逆序
                        dp[k] = Math.max(dp[k],dp[k - j * v] + w * j);
                    }
                }
            }
        }
        System.out.println(dp[V]);
    }
}
