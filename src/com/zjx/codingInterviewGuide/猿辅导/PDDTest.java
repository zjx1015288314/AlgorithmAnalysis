package com.zjx.codingInterviewGuide.猿辅导;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PDDTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        System.out.println(process(N,K));
    }

    public static int process(int n, int k){
        if(n >= k) return 0;
        int min = Integer.MAX_VALUE;
        for (int i = n; i <= k; i++) {
            int res = i + Math.max(process(n,i - 1),process(i + 1,k));
            min = Math.min(min,res);
        }
        return min;
    }
}
