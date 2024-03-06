package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 请写一个整数计算器，支持加减乘除四种运算和括号。
 * 数据范围：0≤∣s∣≤100，保证计算结果始终在整型范围内
 * 要求：空间复杂度：O(n)，时间复杂度O(n)
 *   输入："1+2  返回值：3
 *   输入："(2*(3-4))*5" 返回值：-10
 *   输入："3+2*3*4-1"  返回值：26
 *
 * https://www.nowcoder.com/practice/c215ba61c8b1443b996351df929dc4d4?tpId=295&tqId=1076787&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
 */
public class 表达式字符串求值 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            System.out.println(getValue(str));
            System.out.println(getValue2(str));
        }
    }

    /**
     * 方法一
     * @param exp
     * @return
     */
    private static int getValue(String exp){
        if(exp == null || exp.equals("")) return 0;
        char[] chs = exp.toCharArray();
        Deque<String> deq = new LinkedList<>();
        int pre = 0;
        Deque<String> tmp = new LinkedList<>();
        for (char ch : chs) {
            if (ch >= '0' && ch <= '9') {
                pre = pre * 10 + ch - '0';
            } else if (ch == '(') {
                deq.addLast(String.valueOf(ch));
            } else if (ch == ')') {
                while (!"(".equals(deq.peekLast())) {
                    tmp.addFirst(deq.pollLast());
                }
                addNum(tmp, pre);
                deq.pollLast();   //弹出'('
                pre = getNum(tmp);
            } else {
                // + - * / 运算符
                addNum(deq, pre);
                deq.addLast(String.valueOf(ch));
                pre = 0;
            }
        }
        addNum(deq,pre);
        return getNum(deq);
    }

    private static void addNum(Deque<String> deq, int num){
        if(!deq.isEmpty()){
            String cur = deq.pollLast();
            if(cur.equals("+") || cur.equals("-") || cur.equals("(")){
                deq.addLast(cur);
            }else{
                int preNum = Integer.valueOf(deq.pollLast());
                num = cur.equals("*") ? (preNum * num) : (preNum / num);
            }
        }
        deq.addLast(String.valueOf(num));
    }

    private static int getNum(Deque<String> tmp){
        int res = 0;
        boolean add = true;
        int num;
        while(!tmp.isEmpty()){
            String cur = tmp.pollFirst();
            if(cur.equals("+")){
                add = true;
            }else if(cur.equals("-")){
                add = false;
            }else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    /**
     * 方法二
     */
    private static int getValue2(String exp){
        if(exp == null || exp.equals("")) return 0;
        char[] chs = exp.toCharArray();
        Stack<String> stack1 = new Stack<>();
        int pre = 0;
        Stack<String> stack2 = new Stack<>();
        for (char ch : chs) {
            if (ch >= '0' && ch <= '9') {
                pre = pre * 10 + ch - '0';
            } else if (ch == '(') {
                stack1.push(String.valueOf(ch));
            } else if (ch == ')') {
                Stack<String> tmp = new Stack<>();
                //按顺序插入
                while (!"(".equals(stack1.peek())) {
                    tmp.push(stack1.pop());
                }
                while (!tmp.isEmpty()) {
                    stack2.push(tmp.pop());
                }

                computeNum(stack2, pre);
                stack1.pop();   //弹出'('
                pre = getResNum(stack2);
            } else {
                //+ - * / 运算符
                computeNum(stack1, pre);
                stack1.push(String.valueOf(ch));
                pre = 0;
            }
        }
        computeNum(stack1,pre);
        return getResNum(stack1);
    }

    private static void computeNum(Stack<String> stack, int num) {
        if(!stack.isEmpty()){
            String cur = stack.pop();
            if(cur.equals("+") || cur.equals("-") || cur.equals("(")){
                stack.push(cur);
            }else{
                int preNum = Integer.valueOf(stack.pop());
                num = cur.equals("*") ? (preNum * num) : (preNum / num);
            }
        }
        stack.push(String.valueOf(num));
    }

    private static int getResNum(Stack<String> stack) {
        Stack<String> tmp = new Stack<>();
        while (!stack.isEmpty()) {
            tmp.push(stack.pop());
        }
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while(!tmp.isEmpty()){
            cur = tmp.pop();
            if(cur.equals("+")){
                add = true;
            }else if(cur.equals("-")){
                add = false;
            }else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }
}
