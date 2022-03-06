package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/22
 * @since 1.0.0
 */
public class AddToPalindromeStr {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String res = getPalindrome(line);
        System.out.println(res);
    }
    private static String getPalindrome(String str){
        if(str == null || str.length() < 2) return str;
        char[] chs = str.toCharArray();
        int[][] dp = getDP(chs);
        char[] res = new char[chs.length + dp[0][chs.length - 1]];
        int i = 0;
        int j = chs.length - 1;
        int resl = 0;
        int resr = res.length - 1;
        while(i <= j){
            if(chs[i] == chs[j]){
                res[resl++] = chs[i++];
                res[resr--] = chs[j--];
            }else if(dp[i + 1][j] < dp[i][j - 1]){
                res[resl++] = chs[i];
                res[resr--] = chs[i++];
            }else{
                res[resl++] = chs[j];
                res[resr--] = chs[j--];;
            }
        }
        return String.valueOf(res);
    }

    //
    private static int[][] getDP(char[] chs){
        int[][] dp = new int[chs.length][chs.length];  //表示str[i..j]成为字符串最少需要添加的字符数
        //str[i..j] i为行j为列
        for(int j = 1; j < dp.length; j++){
            dp[j-1][j] = chs[j - 1] == chs[j] ? 0 : 1;  //字符串只有两个字符的基本情况;其实i=j也是基本情况，但默认正好是0
            for(int i = j - 2; i > -1; i--){
                if(chs[i] == chs[j]){
                    dp[i][j] = dp[i + 1][j - 1];
                }else{
                    dp[i][j] = Math.min(dp[i + 1][j],dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }
}
