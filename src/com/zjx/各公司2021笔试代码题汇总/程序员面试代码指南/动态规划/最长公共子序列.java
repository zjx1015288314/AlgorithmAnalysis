package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。
 * 如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下
 * 删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最长公共子序列 {
    /**
     * 动态规划    dp[i][j]表示在text1中截取[0, i)作为String1，text2中截取(0,j]作为String2，
     * 比较以上两个String的公共最长序列的长度
     * O(M*N)   O(M*N)
     */
    public int longestCommonSubsequence1(String text1, String text2) {
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

    /**
     * O(M*N)  O(min(M, N))
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[] dp = new int[len2+1];
        int pre;    //保存左上角元素
        //********i/j为0的初始状态的改进**********
        for(int i = 1; i <= len1; i++){
            pre = 0;
            for(int j = 1; j <= len2; j++){
                int tmp = dp[j];      //最重要的一步
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[j] = pre + 1;
                }else{
                    dp[j] = Math.max(dp[j-1],dp[j]);
                }
                pre = tmp;
            }
        }
        //***********************
        return dp[len2];
    }
}
