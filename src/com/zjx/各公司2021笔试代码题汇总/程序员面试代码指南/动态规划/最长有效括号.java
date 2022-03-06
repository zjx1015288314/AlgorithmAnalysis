package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 */
public class 最长有效括号 {
    public static void main(String[] args) {
        int len = longestValidParentheses1("()(())");
        System.out.println("len=" + len);
    }

    /**
     * 解法一与解法二区别在于dp[]长度的不同导致写法不同
     */
    public static int longestValidParentheses1(String s) {
        if(s == null || s.length() == 0) return 0;

        int maxLen = 0;
        int[] dp = new int[s.length() + 1];

        for(int i = 2; i <= s.length(); i++) {
            if(s.charAt(i - 1) == ')') {
                if(s.charAt(i - 2) == '(') {
                    dp[i] = dp[i - 2] + 2;
                }else if(i - dp[i - 1] - 2 >= 0 && s.charAt(i - dp[i - 1] - 2) == '('){
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;

        int maxLength = 0;
        int[] dp = new int[s.length()];

        for(int i = 1; i < s.length(); i++){
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    //这一步最重要,之前我认为()()()是答案，但是()(())也属于答案,下面就是对该情况的处理
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;                  //dp[i]由三部分组成,dp[i-1],dp[i-1]外部的一组(),该子串前面的dp[i - dp[i - 1] - 2]/0
                }
                maxLength = Math.max(maxLength, dp[i]);
            }
        }
        return maxLength;
    }
}
