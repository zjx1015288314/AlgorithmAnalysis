package com.zjx.各公司2021笔试代码题汇总.大疆;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DJ2020Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] s = br.readLine().split(" ");
        int K =  Integer.parseInt(s[0]);
        System.out.println(process(str,K));
    }

    public static String process(String num,int k){
        if(num == null || num.isEmpty() || num.trim().isEmpty() || k == 0){return num;}
        StringBuffer sb = new StringBuffer(num);
        while(k > 0){
            int i = 0;
            while(i < sb.length() - 1 && sb.charAt(i) <= sb.charAt(i + 1)){
                i++;
            }
            sb.delete(i,i + 1);
            k--;
        }
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.delete(0,1);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
