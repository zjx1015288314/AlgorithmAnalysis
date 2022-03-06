package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最长回文子串 {
    /**
     * @ref{最长回文子序列.longestPalindromeSubseq}
     */
    public String longestPalindrome(String s) {
        char[] chs = s.toCharArray();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxPalindrome = 0;   //指向最终回文串的头索引
        int maxLength = 0;   //最长回文串的长度
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(chs[i] == chs[j]) {
                    //i+1 > j-1时子字符串已经为空,我们要么在fori循环前处理dp[i + 1][j - 1]，要么这里加个判断条件
                    dp[i][j] = i + 1 <= j - 1 ? dp[i + 1][j - 1] : true;
                }
                if(dp[i][j] && maxLength < j - i + 1) {  //maxLength < j - i + 1不能少，否则maxPalindrome错误
                    maxLength = Math.max(maxLength, j - i + 1);
                    maxPalindrome = i;
                }
            }
        }
        return s.substring(maxPalindrome, maxPalindrome + maxLength);
    }

    /**
     * 中心扩展算法:
     * 边界情况即为子串长度为 11 或 22 的情况。我们枚举每一种边界情况，并从对应的子串开始不断地向两边扩展。
     * 如果两边的字母相同，我们就可以继续扩展，例如从 P(i+1,j-1)P(i+1,j−1) 扩展到 P(i,j)P(i,j)；
     * 如果两边的字母不同，我们就可以停止扩展，因为在这之后的子串都不能是回文串了。
     * 聪明的读者此时应该可以发现，「边界情况」对应的子串实际上就是我们「扩展」出的回文串的「回文中心」。
     * 方法二的本质即为：我们枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此
     * 「回文中心」下的最长回文串长度。我们对所有的长度求出最大值，即可得到最终的答案。
     *
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;    //为了兼容奇偶长度的情况
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;   //去掉头尾
    }
}
