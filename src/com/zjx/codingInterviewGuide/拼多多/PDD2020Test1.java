package com.zjx.codingInterviewGuide.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PDD2020Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            System.out.println(cal(Integer.parseInt(str[i])));
        }
    }

    public static int cal(int n){
        if (n==1) return 1;
        if (n==2) return 2;
        else return 1+cal(n/2);
    }
}
