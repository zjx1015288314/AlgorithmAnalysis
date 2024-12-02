package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

/**
 * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 *
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2：
 * 输入：s = "aa", p = "*"
 * 输出：true
 * 解释：'*' 可以匹配任意字符串。
 *
 * 示例 3：
 * 输入：s = "cb", p = "?a"
 * 输出：false
 * 解释：'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *
 * 0 <= s.length, p.length <= 2000
 * s 仅由小写英文字母组成
 * p 仅由小写英文字母、'?' 或 '*' 组成
 *
 * https://leetcode.cn/problems/wildcard-matching/description/
 */
public class 通配符匹配 {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a")); // false
        System.out.println(isMatch("aa", "*")); // true
        System.out.println(isMatch("cb", "?a")); // false
    }

    /**
     * @see 字符串正则匹配#isMatchDp5(String, String)  两个算法很像
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //!!!这段初始化很重要
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for( int j = 1; j <= p.length(); j++) {
                char c = p.charAt(j - 1);
                if (i == 0) {
                    if (c == '*') {
                        dp[i][j] = dp[i][j - 1];
                    }
                    continue;
                }
                // * 可以表示0个  无数个  这里dp[i - 1][j - 1]可以不要, 因为在处理dp[i - 1][j] 时已经包含了左边的dp[i - 1][j - 1]
                if (c == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j];
                } else if (c == '?' || c == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}
