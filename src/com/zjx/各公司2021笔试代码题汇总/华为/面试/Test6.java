package com.zjx.各公司2021笔试代码题汇总.华为.面试;


import java.util.Objects;

/**
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 * 实现 MagicDictionary 类：
 * •	MagicDictionary() 初始化对象
 * •	void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * •	bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
 * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 *
 * 示例：
 * 输入
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 *
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 */
public class Test6 {

    public static void main(String[] args) {
         MagicDictionary magicDictionary = new MagicDictionary();
         String[] str = {"hello", "leetcode"};
         magicDictionary.buildDict(str);
         System.out.println(magicDictionary.search("hello")); // 返回 False
         System.out.println(magicDictionary.search("hhllo")); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
         System.out.println(magicDictionary.search("hell")); // 返回 False
         System.out.println(magicDictionary.search("leetcoded")); // 返回 False
    }

    static class MagicDictionary {

        private String[] strArr;

        public MagicDictionary() {}

        public void buildDict(String[] strArr) {
            this.strArr = strArr;
        }

        public boolean search(String str) {
            return isMatch(strArr, str);
        }

        private boolean isMatch(String[] strArr, String p) {
            if (strArr == null) {
                return false;
            }
            for (String s : strArr) {
                if (isMatch(s, p)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isMatch(String s, String p) {
            if (s == null || p == null) {
                return Objects.equals(s, p);
            }
            if (s.length() != p.length()) {
                return false;
            }

            int[][] dp = new int[s.length() + 1][p.length() + 1];
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= p.length(); j++) {
                    if (s.charAt(i - 1) == p.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    }
                }
            }

            return dp[s.length()][p.length()] == 1;
        }
    }


}
