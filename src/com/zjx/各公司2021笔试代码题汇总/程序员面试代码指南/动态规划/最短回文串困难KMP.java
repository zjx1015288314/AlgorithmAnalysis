package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 *
 */
public class 最短回文串困难KMP {

    /**
     * 基于 KMP 计算出原字符串 s 的前缀数组next，在s^(s的逆序)中匹配 s，匹配到的 s 的前 j 个字符
     * 即为 s 的最长回文前缀。【这种做法节省空间】
     * 时间复杂度O(N)
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        //KMP求next数组
        int[] next = getNextArr(s);

        // !!!在s1(即主串，为s的逆序)中查找s。切记，next数组是根据待查询字符串计算的，并不是主串
        int j = 0;
        String s1 = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < s1.length(); i++) { // 这里i j不能乱
            while (j > 0 && s.charAt(j) != s1.charAt(i)) {
                j = next[j - 1];
            }
            if (s.charAt(j) == s1.charAt(i)) {
                j++;
            }
        }
        return new StringBuilder(s.substring(j)).reverse().append(s).toString();
    }

    /**
     * 解法一的一种拓展, s + "#" + s_reverse 拼接起来后利用KMP求next
     * next[res.length() - 1]即为最长前缀回文字串长度
     * @param s
     * @return
     */
    public String shortestPalindrome1(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        String res = s + "#" + new StringBuilder(s).reverse();
        int[] next = getNextArr(res);
        return new StringBuilder(s.substring(next[res.length() - 1])).reverse().append(s).toString();
    }

    /**
     * @see 最长回文子串 的变种，即开头位置为0的最长回文字串
     * 时间复杂度O(N^2)，过不了
     * @param s
     * @return
     */
    public String shortestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        dp[n][n] = true;
        int maxLen = 1;
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = (i + 1 <= j - 1 ? dp[i + 1][j - 1] : true);
                }
                if (i == 1 && dp[i][j] && (j - i + 1) > maxLen) {
                    maxLen = j - i + 1;
                }
            }
        }
        String s2 = s.substring(maxLen);
        System.out.println("s2:" + s2);
        return new StringBuilder(s2).reverse().append(s).toString();
    }

    private int[] getNextArr(String s) {
        int[] next = new int[s.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
