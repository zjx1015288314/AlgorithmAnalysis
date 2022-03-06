package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

public class 匹配问题 {
    public static void main(String[] args) {
        System.out.println(isMatch("aab","c*a*b"));
    }
    public static boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        //对于i，j起点与终点的选取要记住.i = text.length()是表示匹配串为null时也要进行判断
        //j = pattern.length() - 1;表示模式串末尾
        for (int i = s.length(); i >= 0; i--){
            for (int j = p.length() - 1; j >= 0; j--){
                if(p.charAt(j) == '*'){
                    dp[i][j] = dp[i][j + 1] || (i < s.length() && dp[i + 1][j]);  //注意这里i < s.length()不加会抛异常
                }else{
                    boolean first_match = (i < s.length() &&
                            (p.charAt(j) == s.charAt(i) ||
                                    p.charAt(j) == '?'));
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}
