package com.zjx.DataStructure.Chapter3;

import java.io.*;
import java.util.Stack;

public class CheckBalanceSign {
    /**
     * 栈的一个主要应用是平衡符号。
     * <p>
     * 问题描述： 在编写代码并且编译时，难免会因为少写了一个')'和被编译器报错。也就是说，编译器会去匹配
     * 括号是否匹配。当你输入了一个'('，很自然编译器回去检查你是否有另一个')'符号与之匹配。如
     * 果所有的括号都能够成对出现，那么编译器是能够通过的。否则编译器会报错。例如字符序列“(a+b)”是匹配的，
     * 而字符序列"(a+b]"则不是。
     * <p>
     * 算法描述如下： 创建一个空栈，读取字符序列直到结尾。如果字符是开放符号'(''[''{'，将其入栈；如果是一个封闭
     * 符号')'']''}'，则当栈为空时报错。否则，将栈顶元素弹出。如果弹出的符号不是对应的开放符号，
     * 则报错。当字符序列结束，判断栈是否为空，为空则报错。
     */
    public static boolean isBalanceChar() {
        Stack<Character> stack = new Stack<>();
        String pathname = "D:/IDEA/workspace/a.txt";
        File file = new File(pathname);
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bf = new BufferedReader(isr);
            String line = "";
            while ((line = bf.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '[':
                            stack.push('[');
                            break;
                        case '(':
                            stack.push('(');
                            break;
                        case '{':
                            stack.push('{');
                            break;
                        case '/':
                            if (i < line.length() - 1 && line.charAt(i + 1) == '*') {
                                stack.push('/');
                                stack.push('*');
                            }
                            break;
                        case ']':
                            if (stack.size() == 0 || stack.pop() != '[') {
                                System.out.print("illigal character" + "[]");
                                return false;
                            }
                            break;
                        case ')':
                            if (stack.size() == 0 || stack.pop() != '(') {
                                System.out.print("illigal character" + "()");
                                return false;
                            }
                            break;
                        case '}':
                            if (stack.size() == 0 || stack.pop() != '{') {
                                System.out.print("illigal character" + "{}");
                                return false;
                            }
                            break;
                        case '*':
                            if (i < line.length() - 1 && line.charAt(i + 1) == '/' &&
                                    (stack.size() < 2 || stack.pop() != '*' || stack.pop() != '/')) {
                                System.out.println("illigal character" + "");
                                return false;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            if (!stack.isEmpty())
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        boolean flag = isBalanceChar();
        if (flag) {
            System.out.println("是平衡符号");
        } else {
            System.out.println("不是平衡符号");
        }

    }
}
