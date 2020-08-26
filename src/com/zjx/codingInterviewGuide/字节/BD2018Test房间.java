package com.zjx.codingInterviewGuide.字节;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BD2018Test房间 {
    private static double e = 1e9 + 7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[] arr = new int[N + 1];
        str = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(str[i - 1]);
        }
        System.out.println(process(arr));
    }

    public static int process(int[] arr){
        int len = arr.length;
        int[] dp = new int[len + 1];   //第一次到达i号房间需要移动的次数
        //dp[1] = 1;
        for (int i = 2; i <= len; i++){
            dp[i] = (dp[i - 1] * 2 % 1000000007 - dp[arr[i - 1]] + 2) % 1000000007;
        }
        return dp[len];
    }
}
