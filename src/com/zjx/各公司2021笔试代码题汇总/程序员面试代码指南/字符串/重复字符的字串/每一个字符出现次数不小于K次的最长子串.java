package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串.重复字符的字串;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * 如果不存在这样的子字符串，则返回 0。

 * 示例 1：
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 *
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/description/
 * 时间复杂度O(N*K) 空间复杂度O(K^2)  K为字符集的大小
 */
public class 每一个字符出现次数不小于K次的最长子串 {

    public int longestSubstring(String s, int k) {
        int[] charCount = new int[128];
        for (char c : s.toCharArray()) {
            charCount[c]++;
        }
        String splitStr = "";
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 0 && charCount[i] < k) {
                splitStr = String.valueOf((char) i);
                break;
            }
        }
        if (splitStr.isEmpty()) {
            return s.length();
        }

        String[] splitArr = s.split(splitStr);
        int maxLen = 0;
        for (String str : splitArr) {
            maxLen = Math.max(maxLen, longestSubstring(str, k));
        }
        return maxLen;
    }

}
