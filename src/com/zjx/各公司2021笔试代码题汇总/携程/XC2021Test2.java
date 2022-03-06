package com.zjx.各公司2021笔试代码题汇总.携程;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class XC2021Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);

        int[][] income = new int[N][2];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            income[i][0] = Integer.parseInt(str[0]);
            income[i][1] = Integer.parseInt(str[1]);
        }

        System.out.println(process(income));

    }

    public static int process(int[][] in){
        int len = in.length;
        int[][] m = new int[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                m[i][j] = Math.max(m[i - 1][j] + in[i + j - 1][0],m[i][j - 1] + in[i + j - 1][1]);
            }
        }
        return m[len][len];
    }
}
