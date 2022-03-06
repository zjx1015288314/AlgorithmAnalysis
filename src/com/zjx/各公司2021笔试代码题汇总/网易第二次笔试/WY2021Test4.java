package com.zjx.各公司2021笔试代码题汇总.网易第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class WY2021Test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int[] A = new int[str.length];
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(str[i]);
        }
        str = br.readLine().split(" ");
        int[] B = new int[str.length];
        for (int i = 0; i < B.length; i++) {
            B[i] = Integer.parseInt(str[i]);
        }
        str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);

        int[][] tmp = new int[n][2];
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            tmp[i][0] = Integer.parseInt(str[0]);
            tmp[i][1] = Integer.parseInt(str[1]);
        }
        System.out.println(process(A,B,tmp));

    }

    public static int process(int[] A, int[] B, int[][] arr){
        if (arr == null || arr.length == 0) return 0;
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            set1.add(A[i]);
        }
        for (int i = 0; i < B.length; i++) {
            set2.add(B[i]);
        }
        int num = set1.size();
        int num2 = set2.size();
        return Math.min(num,num2);
    }
}
