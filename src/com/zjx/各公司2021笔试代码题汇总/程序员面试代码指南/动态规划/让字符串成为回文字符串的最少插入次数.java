package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 * 请你返回让 s 成为回文串的 最少操作次数 。
 * 「回文串」是正读和反读都相同的字符串。
 * 示例 1：
 * 输入：s = "zzazz"
 * 输出：0
 * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
 * 示例 2：
 * 输入：s = "mbadm"
 * 输出：2
 * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：找到现有字符串中存在的最大回文子序列，剩下的字符数就是我们需要在现有回文序列上加的
 * 而最大的回文序列其实就是<s, s.reverse()>之间的最大公共子序列
 */
public class 让字符串成为回文字符串的最少插入次数 {
    public int minInsertions(String s) {
        StringBuffer sb = new StringBuffer(s);
        int longestLenCommonSubsequence = longestCommonSubsequence(s, sb.reverse().toString());
        return s.length() - longestLenCommonSubsequence;
    }

    /**
     * @ref{最长公共子序列.longestCommonSubsequence1}
     */
    private int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null) {
            return 0;
        }
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
