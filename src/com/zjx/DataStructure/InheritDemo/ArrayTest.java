package com.zjx.DataStructure.InheritDemo;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayTest {
    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[] dp = new int[len2 + 1];
        int[] pre = new int[len2];
        //********i/j为0的初始状态的改进**********
        for (int i = 1; i <= len1; i++) {
            pre = Arrays.copyOf(dp, len2 + 1);
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = pre[j - 1] + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], pre[j]);
                }
            }
        }
        //***********************
        return dp[len2];
    }

    public static int longCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[] dp = new int[len2 + 1];
        int pre;    //保存左上角元素
        //********i/j为0的初始状态的改进**********
        for (int i = 1; i <= len1; i++) {
            pre = 0;
            for (int j = 1; j <= len2; j++) {
                int tmp = dp[j];      //最重要的一步
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = pre + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                pre = tmp;
            }
        }
        //***********************
        return dp[len2];
    }

    public static void main(String[] args) {
        String s = new StringBuffer("bbbab").reverse().toString();
        System.out.println(longCommonSubsequence("bbbab", s));


    }
}
