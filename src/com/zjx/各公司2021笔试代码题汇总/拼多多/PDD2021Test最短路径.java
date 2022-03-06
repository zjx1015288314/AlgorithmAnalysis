package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PDD2021Test最短路径 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        char[][] matrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = s.charAt(j);
            }
        }
        System.out.println(process(matrix));
//        System.out.println("1e7 : " + (1e9+7));
    }

    public static int process(char[][] matrix){
        int len = matrix.length;
        int[][] dp = new int[len + 1][len + 1];
        dp[0][0] = matrix[0][0] == '*' ? 0 : 1;
        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i - 1][0] + (matrix[i - 1][0] == '*' ? 0 : 1);
        }
        for (int j = 1; j < len; j++) {
            dp[0][j] = dp[0][j - 1] + (matrix[0][j - 1] == '*' ? 0 : 1);
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j < len; j++) {
                if(matrix[i][j] != '*'){
                    dp[i][j] = 1 + Math.min(dp[i][j - 1],dp[i - 1][j]);
                }else{
                    dp[i][j] = Math.min(dp[i][j - 1],dp[i - 1][j]);
                }
            }
        }
        return dp[len - 1][len - 1];
    }
}
