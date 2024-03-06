package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数字相关;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
 *  现在给一串数字，返回有多少种可能的译码结果
 *  数据范围：字符串长度满足0<n≤90
 *  进阶：空间复杂度O(n)，时间复杂度O(n)
 *
 *  输入："12"  返回值：2  说明：2种可能的译码结果（”ab” 或”l”）
 *  https://www.nowcoder.com/practice/046a55e6cd274cffb88fc32dba695668?tpId=295&tqId=1024831&ru=/exam/company&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Fcompany
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

    /**
     * 动态规划 容易理解
     * @param nums
     * @return
     */
    public int solve (String nums) {
        // write code here
        if (nums == null || nums.length() == 0 || nums.equals("0")) {
            return 0;
        }
        // 剪枝
        //当0的前面不是1或2时，无法译码，0种
        for (int i = 1; i < nums.length(); i++) {
            if (nums.charAt(i) == '0')
                if (nums.charAt(i - 1) != '1' && nums.charAt(i - 1) != '2')
                    return 0;
        }
        int[] dp = new int[nums.length() + 1];
        //辅助数组初始化为1
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= nums.length(); i++) {
            //在11-19，21-26之间的情况
            if ((nums.charAt(i - 2) == '1' && nums.charAt(i - 1) != '0') ||
                    (nums.charAt(i - 2) == '2' && nums.charAt(i - 1) > '0' &&
                            nums.charAt(i - 1) < '7'))
                dp[i] = dp[i - 1] + dp[i - 2];
            else
                dp[i] = dp[i - 1];
        }
        return dp[nums.length()];
    }
}
