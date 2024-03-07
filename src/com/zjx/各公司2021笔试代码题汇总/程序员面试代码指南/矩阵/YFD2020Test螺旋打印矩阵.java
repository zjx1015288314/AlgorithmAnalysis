package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YFD2020Test螺旋打印矩阵 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }
        process(arr);
    }

    public static void process(int[][] matrix){
        int topRow = 0;
        int topCol = 0;
        int bottomRow = matrix.length - 1;
        int bottomCol = matrix[0].length - 1;
        while(topRow <= bottomRow && topCol <= bottomCol ){
            printRac(matrix,topRow++,topCol++,bottomRow--,bottomCol--);
        }
    }

    public static void printRac(int[][] matrix, int tR, int tC, int dR, int dC){
        StringBuilder sb = new StringBuilder();
        if (tR == dR){
            for (int i = tC; i <= dC; i++) {
                sb.append(matrix[tR][i]).append(" ");
            }
        }else if(tC == dC){
            for (int i = tR; i <= dR; i++) {
                sb.append(matrix[i][tC]).append(" ");
            }
        }else{
            int curR = tR;
            int curC = tC;

            // 注意这是逆时针打印
            while (curR < dR){
                sb.append(matrix[curR][tC]).append(" ");
                curR++;
            }
            while (curC < dC){
                sb.append(matrix[dR][curC]).append(" ");
                curC++;
            }
            while (curR > tR){
                sb.append(matrix[curR][dC]).append(" ");
                curR--;
            }
            while(curC > tC){
                sb.append(matrix[tR][curC]).append(" ");
                curC--;
            }
        }
        System.out.print(sb);
    }
}
