package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.栈;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true

 * 示例 2：
 *
 * 输入：s = "(]"
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 简单有效的括号 {

    public boolean isValid(String s) {
        Map<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chs = s.toCharArray();
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] == '(' || chs[i] == '{' || chs[i] == '[') {
                stack.push(chs[i]);
            }else {
                if(stack.isEmpty()) {return false;}
                if(chs[i] == ')' && stack.peek() != '(') {
                    return false;
                }
                if(chs[i] == ']' && stack.peek() != '[') {
                    return false;
                }
                if(chs[i] == '}' && stack.peek() != '{') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
