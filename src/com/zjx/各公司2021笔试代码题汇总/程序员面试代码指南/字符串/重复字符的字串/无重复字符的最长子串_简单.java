package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串.重复字符的字串;

/**
 *  给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 *
 *  示例 1:
 *  输入: s = "abcabcbb"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *  示例 2:
 *  输入: s = "bbbbb"
 *  输出: 1
 *  解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *  示例 3:
 *  输入: s = "pwwkew"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *  请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  链接: https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 */
public class 无重复字符的最长子串_简单 {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            map[c]++;
            while (map[c] > 1) {
                char d = s.charAt(left);
                map[d]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
