package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode394字符串解码 { 
    public static void main(String[] args) {
        System.out.println(decodeString("2[abc]3[cd]ef"));
        System.out.println(decodeString1("2[abc]3[cd]ef"));
    }

    /**
     * 使用栈的思想来做    遇到']'弹出至'[' 再弹出左边的数字   合并后放入栈顶
     */
    static int ptr;

    public static String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<>();
        ptr = 0;
        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if ((Character.isLetter(cur)) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {  //']'的情况
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }
        return getString(stk);
    }

    private static String getDigits(String s) {
        StringBuffer sb = new StringBuffer();
        while (s.charAt(ptr) >= '0' && s.charAt(ptr) <= '9') {
            sb.append(s.charAt(ptr++));
        }
        return sb.toString();
    }

    private static String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }


    /**
     * 解法二：数字一个栈  字母一个栈   考虑的情况比较多
     */
    public static String decodeString1(String s) {
        StringBuffer ans = new StringBuffer();
        Stack<Integer> multiStack = new Stack<>();  //数组栈
        Stack<StringBuffer> ansStack = new Stack<>(); //字母栈
        int multi = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) multi = multi * 10 + c - '0';
            else if (c == '[') {
                //[的必定是数字，数字前面很有可能是可以解码的字母(除非是开头)
                ansStack.add(ans);
                multiStack.add(multi);
                ans=new StringBuffer();
                multi = 0;
            } else if (Character.isAlphabetic(c)) {
                ans.append(c);
            } else {
                StringBuffer ansTmp = ansStack.pop();
                int tmp = multiStack.pop();
                for (int i = 0; i < tmp; i++) ansTmp.append(ans);  //解码
                ans = ansTmp;
            }
        }
        return ans.toString();
    }
}
