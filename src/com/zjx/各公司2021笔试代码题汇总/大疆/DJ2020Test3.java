package com.zjx.各公司2021笔试代码题汇总.大疆;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DJ2020Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int X = Integer.parseInt(str[1]);
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(str[0]);
            arr[i][1] = Integer.parseInt(str[1]);
        }
        System.out.println(process(0, 0, N, X, arr));
    }

    public static int process(int sum, int idx, int n, int x, int[][] arr) {
        if (idx >= n) return 0;
        int res = arr[idx][1] + sum;
        int res1 = process(sum, idx + 1, n, x, arr);
        if (res > x) {
            return res1;
        }
        int res2 = process(sum + arr[idx][1], idx + 1, n, x, arr) + arr[idx][0];
        return Math.max(res1, res2);
    }
}
