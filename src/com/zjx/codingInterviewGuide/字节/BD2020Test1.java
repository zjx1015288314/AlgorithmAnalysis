package com.zjx.codingInterviewGuide.字节;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BD2020Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        long K = Long.parseLong(str[2]);   //long类型可表示2^19或者2^18

        //System.out.println(process(N,M,K));
    }
}
