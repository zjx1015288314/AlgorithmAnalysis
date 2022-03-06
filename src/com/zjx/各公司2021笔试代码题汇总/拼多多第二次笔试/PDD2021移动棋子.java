package com.zjx.各公司2021笔试代码题汇总.拼多多第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * N*M的矩阵上分为N*M个小格子，一个格子的上下左右四个格子视为与该格子相邻，相邻的格子为一个队伍，
 * 每个格子上可能有士兵，也可嫩没有士兵，现移动任意格子上的士兵到任意空格子，求移动后的最大队伍士兵量
 * 第一行输入 N M
 * 接下来N行  每行M个士兵 数字0或1代表每个格子里的士兵数量
 */
public class PDD2021移动棋子 {

    static int row = 0;
    static int col = 0;
    static int[][] tmp = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        row = N;
        col = M;
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(str[j]);
            }
        }
    }
}
