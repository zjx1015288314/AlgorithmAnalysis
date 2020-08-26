package com.zjx.DataStructure.Chapter3;

import java.util.Scanner;
import java.util.Stack;

public class EvalPostFix {
    /**
     * 计算后缀表达式的值,后缀表达式：表达式中不包括括号；运算符在操作数后面；属于严格地从左往右一次计算的表达式
     * 例:0!+123+4*(5*6!+7!/8)/9这是正常的中缀表达式，转换为后缀表达式为：0 ! 123 + 4 5 6 ! * 7 ! 8 / + * 9  / +
     *
     * @return
     */
    static double evaluate() {
        Stack<Double> stack = new Stack<>();
        String token;   //接收外界输入
        Double a, b, result = 0.0;
        boolean isNumber;
        //Scanner是一个扫描器，我们录取到键盘的数据(已回车作为输入结束的标志)，先存到缓存区等待读取
        //它判断读取结束的标示是  空白符；比如空格，回车，tab 等等
        //next()方法读取到空白符就结束；nextLine()读取到回车结束也就是“\r”；
        Scanner sc = new Scanner(System.in);
        token = sc.next();
        while (token.charAt(0) != '=') {
            try {
                isNumber = true;     //默认输入是操作数并进行转换,如果转换失败则为操作符
                result = Double.parseDouble(token); //如果token不是数字字符,则会发生NumberFormatException,
                // 这是受查异常,需要显示捕获或者throws声明
            } catch (NumberFormatException e) {
                isNumber = false;
            }
            if (isNumber)
                stack.push(result);
            else {
                switch (token.charAt(0)) {
                    case '+':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b + a);
                        break;
                    case '-':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b - a);
                        break;
                    case '*':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b * a);
                        break;
                    case '/':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b / a);
                        break;
                    case '^':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(Math.pow(b, a));   //b^a,也可写成Math.exp(a*Math.log(b))
                        break;
                }
            }
            token = sc.next();
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        System.out.println("the result is " + evaluate());
    }
}
