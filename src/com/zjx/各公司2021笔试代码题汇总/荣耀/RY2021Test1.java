package com.zjx.各公司2021笔试代码题汇总.荣耀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RY2021Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(",");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[0]);

        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == 'S') {
                    matrix[i][j] = 1;
                } else if (s.charAt(j) == 'H'){
                    matrix[i][j] = 0;
                }
            }
        }
//        System.out.println(process(matrix));
//        System.out.println(process1(matrix));
    }

}
