package com.zjx.各公司2021笔试代码题汇总.拼多多第三次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PDD2021Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] t = br.readLine().split(" ");
        int T = Integer.parseInt(t[0]);
        int[] res = new int[T];
        for (int i = 0; i < T; i++) {
            String[] Ns = br.readLine().split(" ");
            int n = Integer.parseInt(Ns[0]);
            int[][] nums = new int[n][n];
            for (int j = 0; j < n; j++) {
                String[] rowS = br.readLine().split(" ");
                for (int k = 0; k < n; k++) {
                    nums[j][k] = Integer.parseInt(rowS[k]);
                }
            }
            res[i] = process(nums, n);
        }
        for (int i = 0; i < T; i++) {
            System.out.println(res[i]);
        }
    }

    private static int process(int[][] nums, int n) {
        int gap = n / 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int cur = i * gap;
            int count = 0;
            for (int j = 0; j < n; j += gap) {
                if (nums[cur][j] == 1) {
                    count++;
                }
            }
            sb.append(count);
        }
        String s = sb.toString();
        if (s.equals("0444444400")) {
            int i = gap * 5 - 1;
            int j = gap * 5 - 1;
            if (nums[i][j] == 0) {
                return 0;
            } else {
                return 8;
            }
        } else if (s.equals("0232222400")) {
            return 1;
        } else if (s.equals("0462222600")) {
            return 2;
        } else if (s.equals("04424424400")) {
            return 3;
        } else if (s.equals("0234472200")) {
            return 4;
        } else if (s.equals("0262524400")) {
            return 5;
        } else if (s.equals("0422544400")) {
            return 6;
        } else if (s.equals("0662222200")) {
            return 7;
        } else if (s.equals("0444522400")) {
            return 9;
        }
        return -1;
    }

}

