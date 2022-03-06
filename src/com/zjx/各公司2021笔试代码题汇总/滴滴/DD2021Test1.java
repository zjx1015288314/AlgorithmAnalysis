package com.zjx.各公司2021笔试代码题汇总.滴滴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DD2021Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int k = Integer.parseInt(str[0]);
        String input = br.readLine();

        if (k == 1) System.out.println(input);
        else System.out.println(process(input,k));

    }

    public static String process(String str,int k){
        if (str == null || str.length() == 0) return "";
        char[] chs = str.toCharArray();

        int start = 0;
        int end = 0;
        int num = k;
        int s = chs.length / k;
        for (int i = 0; i < chs.length; i++) {
            if (--num == 0){
                s--;
                reverse(chs,start,i);
                num = k;
                start = i + 1;
            }
            if (s == 0 && i == chs.length - 1){
                reverse(chs,start,i);
            }
        }
        return new String(chs);
    }

    public static void reverse(char[] chs, int low, int high){
        int i = low;
        int j = high;
        while (i < j){
            char tmp = chs[i];
            chs[i] = chs[j];
            chs[j] = tmp;
            i++;
            j--;
        }
    }
}
