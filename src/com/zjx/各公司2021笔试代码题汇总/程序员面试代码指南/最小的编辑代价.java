package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定两个字符串str1和str2，再给定三个整数ic，dc和rc，分别代表插入、
 * 删除和替换一个字符的代价，请输出将str1编辑成str2的最小代价。
 * abc
 * adc
 * 5 3 2
 * 输出：2
 * @author zhaojiexiong
 * @create 2020/7/22
 * @since 1.0.0
 */
public class 最小的编辑代价 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        String[] ss = br.readLine().split(" ");
        int ic = Integer.parseInt(ss[0]);
        int dc = Integer.parseInt(ss[1]);
        int rc = Integer.parseInt(ss[2]);
        System.out.print(minCost(s1,s2,ic,dc,rc));  //O(M*N)  O(M*N)
        //System.out.print(minCost1(s1,s2,ic,dc,rc));   //O(M*N)  O(min{M,N})
    }
    public static int minCost(String s1,String s2,int ic,int dc,int rc){
        if(s1 == null || s2 == null){
            return 0;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int row = c1.length + 1;
        int col = c2.length + 1;
        int[][] dp = new int[row][col];
        for(int i = 1; i < row; i++){
            dp[i][0] = dc * i;
        }
        for(int j = 1; j < col; j++){
            dp[0][j] = ic * j;
        }
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                if(c1[i - 1] == c2[j - 1]){ //注意 数组越界
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j],dp[i-1][j] + dc);
                dp[i][j] = Math.min(dp[i][j],dp[i][j-1] + ic);
            }
        }
        return dp[row - 1][col - 1];
    }

    public static int minCost1(String s1,String s2,int ic,int dc,int rc){
        if(s1 == null || s2 == null){
            return 0;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] longs = c1.length >= c2.length ? c1 : c2;
        char[] shorts = c1.length < c2.length ? c1 : c2;
        //题目要求的是str1编辑为str2的代价，这里求的是较短的字符串编辑为较长的字符串的代价，如果这里str1
        //为较长的字符串，那么求的正好相反
        if(c1.length < c2.length){
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for(int i = 1; i <= shorts.length; i++){
            dp[i] = ic * i;
        }
        for(int i = 1; i <= longs.length; i++){
            int leftUp = dp[0];
            dp[0] = dc * i;
            for(int j = 1; j <= shorts.length; j++){
                int tmp = dp[j];   //先保存起来，下一轮循环要用
                if(longs[i - 1] == shorts[j - 1]){ //注意 数组越界
                    dp[j] = leftUp;
                }else{
                    dp[j] = leftUp + rc;
                }
                dp[j] = Math.min(dp[j],tmp + dc);
                dp[j] = Math.min(dp[j],dp[j - 1] + ic);
                leftUp = tmp;
            }
        }
        return dp[shorts.length];
    }
}
