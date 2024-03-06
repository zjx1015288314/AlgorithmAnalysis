package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;


import java.util.Arrays;
import java.util.Stack;

/**
 * 给你一个字符串s，「k 倍重复项删除操作」将会从 s中选择k个相邻且相等的字母，并删除它们，
 * 使被删去的字符串的左侧和右侧连在一起。
 * 你需要对s重复进行无限次这样的删除操作，直到无法继续为止。
 * 在执行完所有删除操作后，返回最终得到的字符串。
 * 本题答案保证唯一。
 * 输入：s = "deeedbbcccbdaa", k = 3
 * 输出："aa"
 * 解释：
 * 先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
 * 再删除 "bbb"，得到 "dddaa"
 * 最后删除 "ddd"，得到 "aa"
 * 提示：
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s 中只含有小写英文字母。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string-ii
 */
public class LC1209删除字符串中的所有相邻重复项II {

    /**
     * 借鉴了@ref{LC1047删除字符串中的所有相邻重复项.removeDuplicates}， 每次到chs[j]都准备向前检查k次，
     * 不够的话直接将chs[j]放到chs[++idx]处作准备,但是复杂度有点高，并没有存储之前的观察结果，
     * 导致每次查看都需要O(k)次
     */
    public String removeDuplicates1(String s, int k) {
        if(s == null || s.length() == 0 || s.isEmpty() || k < 2) return "";

        int idx = -1;
        char[] chs = s.toCharArray();
        for(int i = 0; i < chs.length; i++) {
            if(idx < k - 2 || !judgeDuplivates(chs, idx, i, k)) {
                chs[++idx] = chs[i];
            } else {
                idx -= k - 1;
            }
        }
        return String.valueOf(Arrays.copyOfRange(chs, 0, idx + 1));
    }

    private boolean judgeDuplivates(char[] chs, int idx, int end, int k) {
        for(int i = 0; i < k - 1; i++) {
            if(chs[end] != chs[idx - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对removeDuplicates1的优化，存储了chs[j]之前的字符的出现次数，推进使用
     */
    public String removeDuplicates2(String s, int k) {
        Stack<Integer> counts = new Stack<>();
        char[] sa = s.toCharArray();
        int j = 0;
        for(int i = 0; i < sa.length; i++, j++) {
            sa[j] = sa[i];
            if(j == 0 || sa[j] != sa[j - 1]) {
                counts.push(1);
            }else {
                int count = counts.pop();
                int increment = count + 1;
                if (increment == k) {
                    j = j - k;
                } else {
                    counts.push(increment);
                }
            }
        }
        return new String(sa, 0, j);
    }

    /**
     * 和removeDuplicates2想法类似，不过借助了StringBuilder.delete(),本质上还是System.arrayCopy
     */
    public String removeDuplicates3(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int count[] = new int[sb.length()];  //辅助数组
        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
            }
            if (count[i] == k) {
                sb.delete(i - k + 1, i + 1);  //语法
                i = i - k;
            }
        }
        return sb.toString();
    }
}
