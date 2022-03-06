package com.zjx.各公司2021笔试代码题汇总.奇安信;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QAX2021Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        System.out.println(process(n));
    }

    public static int process(int n){
        if (n <= 0 || n > 36) return 0;
        if (n == 1) return 1;
        int pre = 1;
        int cur = 1;
        int num = 2;
        int res = 0;
        while(num <= n){
            res = pre + cur;
            num++;
            pre = cur;
            cur = res;
        }
        return res;
    }
}
