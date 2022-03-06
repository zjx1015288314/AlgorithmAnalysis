package com.zjx.各公司2021笔试代码题汇总.拼多多第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 第一行两个整数N M     M表示原始背包大小
 * 第2到N+1行  每行有两个数字 Ci  Vi  Ci表示第i件物品占用的背包空间，Ci为负说明这件物品会增加背包空间
 * Vi表示第i件商品的收益，如果Vi为负数表示商品负收益
 *
 * N： 1-20
 * Ci： 0-100
 * Vi： 0-100
 *
 * 前60%数据为非负整数
 */
public class PDD2021背包 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int res = 0;
        int[] C = new int[N + 1];
        int[] V = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            str = br.readLine().split(" ");
            C[i] = Integer.parseInt(str[0]);
            V[i] = Integer.parseInt(str[1]);
        }
        System.out.println(process(C,V,M));
    }

    //01背包过60%
    public static int process(int[] c, int[] v,int m){
        int[] dp = new int[m + 1];
        for (int i = 1; i < c.length; i++) {
            for (int j = m; j >= 0; j--) {
                if (j >= c[i]){
                    dp[j] = Math.max(dp[j],dp[j - c[i]] + v[i]);
                }
            }
        }
        return dp[m];
    }
}
