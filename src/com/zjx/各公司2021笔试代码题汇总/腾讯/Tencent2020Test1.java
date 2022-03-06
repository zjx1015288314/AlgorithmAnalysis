package com.zjx.各公司2021笔试代码题汇总.腾讯;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Tencent2020Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(process(str));
    }

    public static String process(String str){
        Stack<Character> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        char[] chs = str.toCharArray();
        String res = "";
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ']'){
                int num = stack1.pop() - '0';
                StringBuffer sb = new StringBuffer();
                String s = stack2.pop();
                for (int j = 0; j < num; j++) {
                    sb.append(s);
                }
                if (!stack2.isEmpty()){
                    String tmp = stack2.pop() + sb.toString();
                    stack2.push(tmp);

                }
            }else if (chs[i] == '['){
                stack1.push(chs[++i]);
            }else if (chs[i] == '|'){
                StringBuffer sb = new StringBuffer();
                ++i;
                while (i < chs.length && chs[i] >= 'A' && chs[i] <= 'Z'){
                    sb.append(chs[i++]);
                }
                stack2.push(sb.toString());
                i--;
            }else{
                res += chs[i];
            }
        }
        int num1 = stack1.pop() - '0';
        StringBuffer sb = new StringBuffer();
        String s = stack2.pop();
        for (int j = 0; j < num1; j++) {
            sb.append(s);
        }
        return res + sb;
    }
}
