package com.zjx.codingInterviewGuide.猿辅导;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YFD2020Test直播质量 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        long S = Long.parseLong(str[1]);
        int[] arr = new int[N];
        str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        System.out.println(process(arr,S));
    }

    public static long process(int[] arr, long s){
        long sum = 0;
        int start = 0;
        long maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length;) {
            if(sum + arr[i] > s){
                sum -= arr[start];
                start++;
                continue;
            }
            sum += arr[i];
            maxLen = Math.max(maxLen,i - start + 1);
            i++;
        }
        return maxLen;
    }
}
