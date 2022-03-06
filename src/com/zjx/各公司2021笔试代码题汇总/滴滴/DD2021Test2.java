package com.zjx.各公司2021笔试代码题汇总.滴滴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DD2021Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int T = Integer.parseInt(str[0]);


        String s = "";
        for (int i = 0; i < T; i++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int k = Integer.parseInt(str[2]);

            int[][] arr = new int[m][3];
            for (int j = 0; j < m; j++) {
                str = br.readLine().split(" ");
                arr[j][0] = Integer.parseInt(str[0]);
                arr[j][1] = Integer.parseInt(str[1]);
                arr[j][2] = Integer.parseInt(str[2]);
            }
            boolean res = process(n,m,k,arr);
            s = (res ? s + "Yes" : s + "No") + "\n";
        }
        System.out.println(s);
    }

    public static boolean process(int n, int m, int k, int[][] arr){
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            int left = arr[i][0];
            int right = arr[i][1];
            int price = arr[i][2];
            if (price <= k){
                visited[left] = true;
                visited[right] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]){
                return false;
            }
        }
        return true;
    }
}
