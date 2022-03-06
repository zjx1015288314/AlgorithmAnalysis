package com.zjx.各公司2021笔试代码题汇总.字节.第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BD2021Test1KMP求循环子串 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(process(str));
    }

    public static String process(String str){
        if (str == null || str.isEmpty()) return "";
        if(str.length() == 1) return str;
        int[] nextArr = getNextArr(str);
        int[] nextArr1 = getNextArr1(str);
        System.out.println(Arrays.toString(nextArr));
        System.out.println(Arrays.toString(nextArr1));
        return "";
    }

    /**
     * next[i]表示以next[i - 1]为结尾的字符串(不包含开头)和以next[0]为开头的字符串(不包含结尾)的最大匹配长度
     * 所以next[0]=-1   next[1]=0
     * @param str
     * @return
     */
    private static int[] getNextArr(String str) {
        int len = str.length();
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < len){
            if (str.charAt(pos - 1) == str.charAt(cn)){
                next[pos++] = ++cn;
            }else if (cn > 0){
                cn = next[cn];
            }else{
                next[pos++] = 0;
            }
        }
        return next;
    }

    public int getIndexOf(String s, String m){
        if(s == null || m == null || m.length() < 1 || s.length() < m.length()){return -1;}
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArr(m);
        while (si < ss.length && mi < ms.length){
            if (ss[si] == ms[mi]){
                si++;
                mi++;
            }else if (next[mi] == -1){
                si++;
            }else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    /**
     * 两个next数组的定义不同   这个next[i]表示以next[i]为结尾的字符串(不包含开头)和以next[0]为
     * 开头的字符串(不包含结尾)的最大匹配长度
     * @param str
     * @return
     */
    private static int[] getNextArr1(String str) {
        int i = 0, j = 0;
        int len = str.length();
        int[] next = new int[len];
        next[i++] = j;
        for (; i < len; i++) {
            while (j != 0 && str.charAt(i) != str.charAt(j)) j = next[j - 1];
            if (str.charAt(i) == str.charAt(j)) j++;
            next[i] = j;
        }
        return next;
    }
}
