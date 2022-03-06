package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.背包;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 有若干[30,50,100]优惠券，求给定一个数值求最优的方案。
 * 例如∶价格是40=>[30]80 =>[30，50].110=>[30,30,50] 此策略为策略一
 */
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
        System.out.println(process(moneys, dest));
    }

    public static List<Integer> process(int[] arr, int dest) {
        int len = arr.length;
        int[][] dp = new int[len][dest + 1];

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= dest; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; arr[i] * k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - k * arr[i]] + k * arr[i]);
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        int j = dest;
        for (int i = len - 1; i >= 1; i--) {
            //两种策略，该策略一为尽量使用小面值的钱币
//            if (dp[i][j] == dp[i - 1][j]) {
//                continue;
//            }
            int maxSum = Integer.MIN_VALUE;
            int idx = -1;
            for (int k = 1; arr[i] * k <= j; k++) {
                if(dp[i - 1][j - k * arr[i]] + k * arr[i] > maxSum){
                    maxSum = dp[i - 1][j - k * arr[i]] + k * arr[i];
                    idx = k;
                }
            }
            //该策略为尽量使用大面值的钱币
            if (idx == -1 && dp[i][j] == dp[i - 1][j]){
                continue;
            }else{
                //将idx个arr[i]添加到list
                for (int k = 0; k < idx; k++) {
                    list.add(arr[i]);
                }
                j -= idx * arr[i];
            }
        }
        return list;
    }
}
