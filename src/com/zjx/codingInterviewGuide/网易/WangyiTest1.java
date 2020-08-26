package com.zjx.codingInterviewGuide.网易;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WangyiTest1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long[] arr = new long[n];
        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        System.out.println(process(arr));

    }
    public static long process(long[] arr){
        if(arr == null || arr.length == 0) return 0;
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] >= 2){
                long size = arr[i] / 2;
                count += size;
            }
        }
        return count;
    }
}
