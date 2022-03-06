package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.栈;

import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1
 * 是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 *
 * 输入：
 * [1,2,3,4,5],[4,3,5,1,2]
 * 返回值：
 * false
 */
public class 压栈出栈顺序 {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;
        for(; i < popA.length; i++) {
            if(!stack.isEmpty() && stack.peek() == popA[i]) {
                stack.pop();
                continue;
            }
            while(j < pushA.length && pushA[j] != popA[i]) {
                stack.push(pushA[j]);
                j++;
            }

            if(j < pushA.length) {
                j++;
                continue;
            }
        }
        return stack.isEmpty();
    }

    public boolean IsPopOrder1(int [] pushA,int [] popA) {
        if (pushA.length == 0 || popA.length == 0 || popA.length != pushA.length)
            return false;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        //每次入栈后都尝试将现有栈中所有元素按出栈顺序出栈，如果无法出栈，则可能出栈元素还未入栈，进入下一次循环
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);

            while (!stack.isEmpty() && stack.peek() == popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
