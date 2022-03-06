package com.zjx.各公司2021笔试代码题汇总.移动;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YD2021Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        process(N);
    }

    public static void process(int N){
        int sum = (int) Math.pow(2,N + 2);
        System.out.println(sum);
        for (int i = 0; i < N; i++) {
            sum /= 2;
            System.out.println(sum);
        }
    }
}
