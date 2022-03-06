package com.zjx.各公司2021笔试代码题汇总.腾讯;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TX2021Test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);

        int[] arr = new int[n];
        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
//        process(arr);
        process1(arr);
    }

    private static  void process1(int[] arr){
        int[] tmp = Arrays.copyOf(arr,arr.length);
        Arrays.sort(arr);
        int mid = arr.length / 2 - 1;
        for (int i = 0; i < tmp.length; i++) {
            if(tmp[i] <= arr[mid]){
                System.out.println(arr[mid + 1]);
            }else{
                System.out.println(arr[mid]);
            }
        }
    }

    private static void process(int[] arr) {
        Arrays.sort(arr);

        if (arr.length == 1){
            System.out.println(arr[0]);
            return;
        }else if (arr.length == 2){
            System.out.println(arr[1]);
            System.out.println(arr[2]);
            return;
        }
        int num = arr.length / 2 - 1; //左边的数
        for (int i = 0; i < arr.length; i++) {
            int llow = 0;
            int lhigh = 0;
            int rlow = 0;
            int rhigh = 0;
            if(i == 0){
                rlow = 1;
                rhigh = arr.length - 1;
                System.out.println(arr[(rlow + rhigh) / 2]);
            }else if (i == arr.length - 1){
                llow = 0;
                lhigh = arr.length - 2;
                System.out.println(arr[(llow + lhigh) / 2]);
            }else{
                llow = 0;
                lhigh = i - 1;
                rlow = i + 1;
                rhigh = arr.length - 1;
                int leftNum = lhigh - llow + 1;
                int rightNum = rhigh - rlow + 1;
                if (leftNum <= num){
                    System.out.println(arr[rlow + num - leftNum]);
                }else if (rightNum <= num){
                    System.out.println(arr[lhigh - num + rightNum]);
                }
            }
        }
    }
}
