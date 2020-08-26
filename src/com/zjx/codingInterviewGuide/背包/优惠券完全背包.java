package com.zjx.codingInterviewGuide.背包;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class 优惠券完全背包 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);  //[30,50,100]
        int dest = Integer.parseInt(str[1]);  //110
        int[] moneys = new int[N + 1];
        str = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            moneys[i] = Integer.parseInt(str[i - 1]);
        }
        System.out.println(process(moneys,dest));
    }

    public static List<Integer> process(int[] arr, int dest){
        int len = arr.length;
        int[][] dp = new int[len][dest + 1];

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= dest; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; arr[i] * k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j],dp[i][j - k * arr[i]] + k * arr[i]);
                }
            }
        }

        return new LinkedList<>();
    }
}
