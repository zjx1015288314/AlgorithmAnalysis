package com.zjx.各公司2021笔试代码题汇总.字节.第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BD2021Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[] arr = new int[N + 1];
        str = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(str[i - 1]);
        }
        //System.out.println(process(arr));
    }
}
