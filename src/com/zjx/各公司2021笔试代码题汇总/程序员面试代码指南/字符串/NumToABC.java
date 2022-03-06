package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/20
 * @since 1.0.0
 */
public class NumToABC {

    private static final int M = 1000_000_007;
    private final static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        //System.out.println(solution(s));
        System.out.println(solution1(s));

        System.out.println(solution2(s.toCharArray(), 0));
    }

    private static int solution(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        char[] c = s.toCharArray();
        int nextnext = 1;   //下一个合法的转换数
        int next = c[len - 1] == '0' ? 0 : 1;   //当前合法的转换数
        int cur = 0;
        for (int i = c.length - 2; i >= 0; i--) {
            if (c[i] == '0') {
                cur = 0;
                nextnext = next;
                next = cur;
            } else {
                cur = next;
                if ((c[i] - '0') * 10 + c[i + 1] - '0' < 27) {
                    cur += nextnext;
                }
                nextnext = next;
                next = cur;
            }
        }
        return next;
    }

    private static int solution1(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        char[] c = s.toCharArray();
        int cur = c[len - 1] == '0' ? 0 : 1;
        int next = 1;
        int tmp = 0;

        for (int i = len - 2; i >= 0; i--) {
            if (c[i] == '0') {
                next = cur;
                cur = 0;
            } else {
                tmp = cur;
                if (((c[i] - '0') * 10 + c[i + 1] - '0') < 27) {
                    cur = (next + cur) % MOD;
                }
                next = tmp;
            }
        }
        return (cur % MOD);
    }

    private static int solution2(char[] c, int index) { //index表示当前以index指向的字母开头
        if (index == c.length) return 1;

        if (c[index] == '0') return 0;

        int res = solution2(c, index + 1);
        if (index + 1 < c.length && (c[index] - '0') * 10 + c[index + 1] - '0' < 27)
            res += solution2(c, index + 2);
        return res % MOD;
    }
}
