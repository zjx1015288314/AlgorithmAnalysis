package com.zjx.DataStructure.Chapter3;

import java.util.Stack;

public class InfixToPostfix {
    /**
     * 数据结构与算法 3.22 3.23
     * 此函数旨在将中缀表达式转换为后缀表达式,前提是中缀表达式是正确的
     * 每次在栈中放入操作符之前,在输出字符串out最后添加' ';每次从栈中取出操作符之前，也在out后添加' '
     * 目的是operand和operator之间都有空格。例123+4 变成 123 4 +,而不是1234+
     */
    public static String getrp(String s) {
        Stack<Character> optr = new Stack<>();    //optr为运算符栈
        char[] arr = s.toCharArray();
        String out = "";              //返回字符串
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch >= '0' && ch <= '9') {
                out += ch;
            } else {
                switch (ch) {
                    case '(':
                        optr.push(ch);
                        break;
                    case '^':
                        out += ' ';
                        optr.push(ch);
                        break;
                    case '*':
                    case '/':
                        while (!optr.empty() && (optr.peek() == '*' || optr.peek() == '/' || optr.peek() == '^')) {
                            out += ' ';
                            out += optr.pop();
                        }
                        out += ' ';
                        optr.push(ch);
                        break;
                    case '+':
                    case '-':
                        //这里empty是Stack的方法,isEmpty是Collection的方法
                        while (!optr.empty() && (optr.peek() != '(')) {
                            out += ' ';
                            out += optr.pop();
                        }
                        out += ' ';
                        optr.push(ch);
                        break;
                    case ')':
                        while (!optr.empty() && optr.peek() != '(') {
                            out += ' ';
                            out += optr.pop();
                        }
                        optr.pop();
                        break;
                }
            }


        }
        while (!optr.empty()) {
            out += ' ';
            out += optr.pop();
        }
        return out;
    }

    /**
     * 计算生成的后缀表达式的值(逆波兰表达式)
     */
    public static double calrp(String rp) {
        Stack<Double> v = new Stack<>();
        char[] arr = rp.toCharArray();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            Character ch = arr[i];
            if (ch >= '0' && ch <= '9') {
                Double num = Double.valueOf(ch - '0');
                for (; i < len - 1; i++) {
                    ch = arr[i + 1];
                    if (ch >= '0' && ch <= '9') {
                        num = num * 10 + Double.valueOf(ch - '0');
                    } else
                        break;
                }
                v.push(num);
            } else if (ch == ' ')
                continue;
            else
                v.push(getv(ch, v.pop(), v.pop()));
        }
        return v.pop();
    }

    public static double getv(char op, Double f1, Double f2) {
        if (op == '+') return f2 + f1;
        else if (op == '-') return f2 - f1;
        else if (op == '*') return f2 * f1;
        else if (op == '/') return f2 / f1;
        else if (op == '^') return Math.pow(f2, f1);
        else return Float.valueOf(-0);
    }

    public static void main(String[] args) {
        System.out.println(getrp("1+4*2^3^2-2^9/4"));      //1 4 2 3 2 ^ ^ * + 2 9 ^ 4 / -
        System.out.println(calrp(getrp("1+4*2^3^2-2^9/4")));  //1921
        System.out.println(getrp("123+4*5+(6+7*8)-9"));    //123 4 5 * + 6 7 8 * + + 9 -
        System.out.println(calrp(getrp("123+4*5+(6+7*8)-9")));  //196.0
    }

}