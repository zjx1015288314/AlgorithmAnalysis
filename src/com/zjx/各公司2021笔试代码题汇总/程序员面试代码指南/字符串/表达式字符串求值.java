package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author zhaojiexiong
 * @create 2020/6/23
 * @since 1.0.0
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
        for(int i = 0; i < chs.length; i++){
            if(chs[i] >= '0' && chs[i] <= '9'){
                pre = pre * 10 + chs[i] - '0';
            }else if(chs[i] == '('){
                deq.addLast(String.valueOf(chs[i]));
            }else if(chs[i] == ')'){
                while(!"(".equals(deq.peekLast())){
                    tmp.addFirst(deq.pollLast());
                }
                addNum(tmp,pre);
                deq.pollLast();   //弹出'('
                pre = getNum(tmp);
            }else{
                // + - * / 运算符
                addNum(deq,pre);
                deq.addLast(String.valueOf(chs[i]));
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
        String cur = null;
        int num = 0;
        while(!tmp.isEmpty()){
            cur = tmp.pollFirst();
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
        for(int i = 0; i < chs.length; i++){
            if(chs[i] >= '0' && chs[i] <= '9'){
                pre = pre * 10 + chs[i] - '0';
            }else if(chs[i] == '('){
                stack1.push(String.valueOf(chs[i]));
            }else if(chs[i] == ')'){
                Stack<String> tmp = new Stack<>();
                //按顺序插入
                while(!"(".equals(stack1.peek())){
                    tmp.push(stack1.pop());
                }
                while (!tmp.isEmpty()) {
                    stack2.push(tmp.pop());
                }

                computeNum(stack2,pre);
                stack1.pop();   //弹出'('
                pre = getResNum(stack2);
            }else{
                //+ - * / 运算符
                computeNum(stack1, pre);
                stack1.push(String.valueOf(chs[i]));
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
