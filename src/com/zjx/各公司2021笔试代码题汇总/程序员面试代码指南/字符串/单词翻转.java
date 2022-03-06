package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

/**
 * “nowcoder. a am I” 翻转为 “I am a nowcoder.”
 * 如果是“  nowcoder. a am I  ”翻转结果去除左右多余空格 “I am a nowcoder.”
 *
 * @link https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 *
 * 总共翻转两遍：整体反转一遍，每个单词翻转一遍
 */
public class 单词翻转 {

    public String ReverseSentence1(String str) {
        if (str == null) return "";
        char[] chs = str.toCharArray();
        reverse(chs, 0, chs.length - 1);

        int start = 0;
        int end = start;
        while (start < chs.length) {
            if (chs[start] == ' ') {
                start++;
                end++;
            } else if (end == chs.length || chs[end] == ' ') {
                reverse(chs, start, end - 1);
                end++;
                start = end;
            } else {
                end++;
            }
        }
        return new String(chs).trim();
    }

    /**
     * 偷懒做法，引用内置API
     * @param str
     * @return
     */
    public String ReverseSentence2(String str) {
        String[] strs = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for(int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i] + " ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private void reverse(char[] chs, int start, int end) {
        while(start < end) {
            swap(chs, start++, end--);
        }
    }

    private void swap(char[] chs, int idx1, int idx2) {
        char tmp = chs[idx1];
        chs[idx1] = chs[idx2];
        chs[idx2] = tmp;
    }
}
