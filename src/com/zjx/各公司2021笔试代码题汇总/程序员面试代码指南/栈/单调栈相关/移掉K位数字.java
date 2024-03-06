package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.栈.单调栈相关;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个以字符串表示的非负整数num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 * 示例 1 ：
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * 示例 2 ：
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 *
 * 思路:第一轮循环如果前面的数字比后面大则删除前面的数字，完成之后结果是一个非降序列，
 *  此时如果还需要删除(k！=0),则从后往前依次删除
 */
public class 移掉K位数字 {

    public static void main(String[] args) {
        removeKdigits1("1432219", 3);
    }

    /**
     * 利用双端队列的特性，处理的时候以栈的方式从尾部处理，求结果时从头部处理
     */
    public static String removeKdigits1(String num, int k) {
        if(num == null || ("").equals(num) || ("").equals(num.trim()) || k==0 ){
            return num;
        }
        if(num.length() <= k || num.length() > 10002){
            return "0";
        }
        Deque<Character> deque = new LinkedList<Character>();
        for(char digit : num.toCharArray()) {
            while(deque.size() > 0 && k > 0 && deque.peekLast() > digit) {
                deque.removeLast();
                k -= 1;
            }
            deque.addLast(digit);
        }

        /* remove the remaining digits from the tail. */
        for(int i = 0; i < k; ++i) {
            deque.removeLast();
        }
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        for(char digit: deque) {
            if(leadingZero && digit == '0') continue;  //前面是多个0的时候跳过
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();  //如果一个也没有则返回"0"
    }

    /**
     * 解法二：直接在原数组上操作
     */
    public static String removeKdigits2(String num, int k) {
        if(num == null || num.length() == 0 || num.length() < k) {
            return "";
        }

        char[] chs = num.toCharArray();
        for(int i = 1; i < chs.length; i++) {
            int idx = i - 1;
            while(idx >= 0 && (chs[idx] == '#' || chs[idx] > chs[i]) && k > 0) {
                if(chs[idx] != '#') {
                    chs[idx] = '#';
                    k--;
                }
                idx--;
            }
        }

        if(k != 0) {
            for(int i = chs.length - 1; i >= 0; i--) {
                if(chs[i] != '#' && k > 0) {
                    chs[i] = '#';
                    k--;
                }
            }
        }

        StringBuffer sb = new StringBuffer("0");
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] != '#') {
                sb.append(String.valueOf(chs[i]));
            }
        }
        while(sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
