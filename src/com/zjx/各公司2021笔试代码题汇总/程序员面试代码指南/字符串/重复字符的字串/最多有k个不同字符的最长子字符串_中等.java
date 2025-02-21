package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串.重复字符的字串;

/**
 * 给定字符串S，找到最多有k个不同字符的最长子串T
 * 样例 1:
 * 输入: S = "eceba" 并且 k = 3
 * 输出: 4
 * 解释: T = "eceb"
 *
 * 样例 2:
 * 输入: S = "WORLD" 并且 k = 4
 * 输出: 4
 * 解释: T = "WORL" 或 "ORLD"
 * O(n) 时间复杂度
 * https://www.lintcode.com/problem/386
 */
public class 最多有k个不同字符的最长子字符串_中等 {

    public static void main(String[] args) {
        int len = lengthOfLongestSubstringKDistinct("eceba", 2);
        System.out.println(len);
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] charCount = new int[128];
        int left = 0, right = 0, cnt = 0;
        int maxLen = 0;
        while (right < s.length()){
            char c = s.charAt(right);
            if (charCount[c] == 0) {
                cnt++;
            }
            charCount[c]++;
            while (cnt > k) {
                charCount[s.charAt(left)]--;
                if (charCount[s.charAt(left)] == 0) {
                    cnt--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }

}
