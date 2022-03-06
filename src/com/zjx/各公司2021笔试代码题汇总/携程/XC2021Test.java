package com.zjx.各公司2021笔试代码题汇总.携程;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class XC2021Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int m = Integer.parseInt(str[0]);

        int[] num = new int[3];
        for (int i = 0; i < 3; i++) {
            String s = br.readLine();
            num[i] = Integer.parseInt(s);
        }
        str = br.readLine().split(" ");
        int price = Integer.parseInt(str[0]);
        process(m,num,price);

    }

    public static void process(int m,int[] num, int price){
        int res = 0;
        for (int i = 0; i < m; i++) {
            int curPrice = 0;
            int j = 2;
            while (j >= 0) {
                if (num[j] == 0){
                    j--;
                    continue;
                }
                if (curPrice >= price) break;
                curPrice += num[j];
                res++;
                num[j]--;
            }
            int remain = curPrice - price;
            while (remain > 0){
                if (remain >= 100){
                    int a = remain / 100;
                    remain %= 100;
                    num[2] += a;
                }else if (remain >= 50 && remain < 100){
                    int b = remain / 50;
                    remain %= 50;
                    num[1] += b;
                }else{
                    int c = remain / 10;
                    remain %= 10;
                    num[0] += c;
                }
            }
        }
        System.out.println(res);
    }
}
