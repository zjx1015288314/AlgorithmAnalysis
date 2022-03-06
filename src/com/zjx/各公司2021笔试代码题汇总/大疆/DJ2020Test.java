package com.zjx.各公司2021笔试代码题汇总.大疆;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DJ2020Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int P = Integer.parseInt(str[1]);

        int[][] arr = new int[P][3];
        for (int i = 0; i < P; i++) {
            str = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(str[0]);
            arr[i][1] = Integer.parseInt(str[1]);
            arr[i][2] = Integer.parseInt(str[2]);
        }
        str = br.readLine().split(" ");
        int X = Integer.parseInt(str[0]);
        System.out.println(process(arr,X,N));
    }

    public static long process(int[][] arr,int dest,int nums){
        long[][] m = new long[nums][nums];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int start = arr[i][0];
            int end = arr[i][1];
            m[start][end] = arr[i][2];
            //m[end][start] = arr[i][2];
        }
        long[] shortPath = new long[nums];
        int[] visited = new int[nums];
        visited[0] = 1;
        for (int count = 1; count < nums; count++) {
            int k = -1;
            long dmin = Integer.MAX_VALUE;
            for (int i = 0; i < nums; i++) {
                if(visited[i] == 0 && m[0][i] < dmin){
                    dmin = m[0][i];
                    k = i;
                }
            }
            shortPath[k] = dmin;
            visited[k] = 1;
            for (int i = 0; i < nums; i++) {
                if(visited[i] == 0 && m[0][k] + m[k][i] < m[0][i]){
                    m[0][i] = m[0][k] + m[k][i];
                }
            }
        }
        return shortPath[dest];
    }
}
