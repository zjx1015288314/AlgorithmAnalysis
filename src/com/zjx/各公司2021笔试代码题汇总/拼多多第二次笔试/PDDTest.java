package com.zjx.各公司2021笔试代码题汇总.拼多多第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class PDDTest {
    static int row = 0;
    static int col = 0;

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
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    set1.add(i + "_" + j);
                } else {
                    set2.add(i + "_" + j);
                }
            }
        }
        int res = 0;
        for (String s : set1) {
            int i = s.charAt(0) - '0';
            int j = s.charAt(2) - '0';
            for (String ss : set2) {
                int x = ss.charAt(0) - '0';
                int y = ss.charAt(2) - '0';
                matrix[x][y] = 0;
                matrix[i][j] = 1;
                res = Math.max(process(matrix),res);
                matrix[x][y] = 1;
                matrix[i][j] = 0;
            }
        }
        System.out.println(ans);
    }

    static int ans = 0;
    public static int process(int[][] matrix) {
        boolean[][] visited = new boolean[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    block(matrix,i,j,1);
                    count++;
                }
            }
        }
        return count;
    }

    public static void block(int[][] m,int i, int j,int count){
        ans = Math.max(ans,count);
        m[i][j] = 4;
        if(i < m.length - 1 && m[i + 1][j] == 1){
            block(m,i +1,j,count + 1);
        }
        if(i > 0 && m[i - 1][j] == 1){
            block(m,i - 1,j,count + 1);
        }
        if(j < m[0].length - 1 && m[i][j + 1] == 1){
            block(m,i,j + 1,count + 1);
        }
        if(j > 0 && m[i][j - 1] == 1){
            block(m,i,j - 1,count + 1);
        }
    }
}
