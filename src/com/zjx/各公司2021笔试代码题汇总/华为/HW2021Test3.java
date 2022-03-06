package com.zjx.各公司2021笔试代码题汇总.华为;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 01背包
 */
public class HW2021Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int K = Integer.parseInt(str[0]);
        str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);

        int[] w = new int[N + 1];
        int[] v = new int[N + 1];
        str = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            w[i] = Integer.parseInt(str[i - 1]);
        }
        str = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            v[i] = Integer.parseInt(str[i - 1]);
        }
        System.out.println(process(w,v,K));
    }

    public static int process(int[] w, int[] v, int k){
        if (w.length == 0) return 0;
        int[] dp = new int[k + 1];

        for (int i = 1; i < w.length; i++) {
            for (int j = k; j >= 0; j--) {
                if(j >= w[i]){
                    dp[j] = Math.max(dp[j],dp[j - w[i]] + v[i]);
                }
            }
        }
        return dp[k];
    }
}
