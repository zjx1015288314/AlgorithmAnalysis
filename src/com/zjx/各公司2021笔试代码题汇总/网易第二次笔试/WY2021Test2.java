package com.zjx.各公司2021笔试代码题汇总.网易第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WY2021Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
//        System.out.println(process(str));
        System.out.println(process1(str));
    }

    public static int process(String str) {
        if (str == null || str.isEmpty()) return 0;
        int res = dfs(str, 0);
        return res;
    }


    public static int process1(String str) {
        if (str == null || str.isEmpty()) return 0;
        int len = str.length();
        int[] dp = new int[len + 1];
        dp[len] = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (dp[i] == 0){
                dp[i] = dp[i + 1] + 1;
            }else{
                dp[i] = dp[i + 1] + 1;
                if (i <= len - 2) dp[i] += dp[i + 2];
                if(i <= len - 3); dp[i] += dp[i + 3];
            }
        }
        return dp[0];
    }

    public static int dfs(String str, int index) {
        if (index >= str.length()) return 1;
        int res = 0;
        if (str.charAt(index) == '1') {
            res += dfs(str, index + 1);
            if (index < str.length() - 1) res += dfs(str, index + 2);
            if (index < str.length() - 2) res += dfs(str, index + 3);
        } else {
            res += dfs(str, index + 1);
        }
        return res;
    }
}
