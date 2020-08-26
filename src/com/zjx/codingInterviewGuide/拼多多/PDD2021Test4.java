package com.zjx.codingInterviewGuide.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 未完成 插头DP
 */
public class PDD2021Test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = null;
        boolean[][] b = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (str[j].equals("#")){
                    b[i][j] = true;
                }
            }
        }
        System.out.print(getRes(b));
    }

    public static int getRes(boolean[][] b){
        int[][] arr = {{-1,0},{0,1},{1,0},{0,-1}};
        int sum = 0;
        boolean[][] flag = new boolean[b.length][b[0].length];
        int[][] tmp = new int[b.length][b[0].length];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j]){
                    for (int k = 0; k < arr.length; k++) {
                        int left = 0;
                        int up = 0;
                        int x1 = i + arr[i][0];
                        int y1 = j + arr[i][1];
                        int x2 = i + arr[i][0];
                        int y2 = j + arr[i][1];
                        if (x1 >= 0 && x1 < b.length && y1 >= 0 && y1 < b[0].length){
                            if(b[x1][y1]) {
                                flag[x1][y1] = true;
                                tmp[i][j] = tmp[x1][y1] - 1;
                            }
                        }
//                        if (x1 < 0 || x1 >= b.length || y1 < 0 || y1 >= b[0].length){continue;}

                        if (x2 >= 0 && x2 < b.length && y2 >= 0 && y2 < b[0].length){
                            if(b[x2][y2]) {
                                flag[x2][y2] = true;
                                tmp[i][j] = tmp[x2][y2] - 1;
                            }
                        }
//                        if (x2 < 0 || x2 > b.length || y2 < 0 || y2 > b[0].length){continue;}
                    }
                }
            }
        }
        return sum;
    }

    public static int process(boolean[][] b,int x, int y,boolean[][] flag){
        int res = 1;
        int num = 6;
        int[][] arr = {{-1,-1},{-1,1},{1,-1},{1,1}};
        for (int i = 0; i < 4; i++) {
            int x1 = x + arr[i][0];
            int y1 = y + arr[i][1];
            if (x1 < 0 || x1 > b.length || y1 < 0 || y1 > b[0].length){continue;}
            if(b[x1][y1]){

            }
        }
        return 1;
    }

}

