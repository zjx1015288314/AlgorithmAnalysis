package com.zjx.各公司2021笔试代码题汇总.网易第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WY2021Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int[] arr = new int[str.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        System.out.println(process(arr));
    }
    public static int process(int[] str){
        int len = str.length;
        int low = 0;
        int high = str.length - 1;
        while (str[low] == 0){
            low++;
        }
        int l = low;
        while (str[high] == 0){
            high--;
        }
        int h = high;
        int res = 1;
        int count = 0;
        while (low < high){
            if(str[low++] == 1){
                count = 0;
            }else{
                count++;
                res = Math.max(res,(count + 1) / 2);
            }
        }
        return Math.max(res, Math.max(l,len - 1 - h));
    }
}
