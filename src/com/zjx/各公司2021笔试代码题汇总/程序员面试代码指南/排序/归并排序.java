package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/7/10
 * @since 1.0.0
 */
public class 归并排序 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] arr = new int[len];
        String[] ss = br.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(ss[i]);  //2 7 8 3 1 6 9 0 5 4 9 19 12 16 14 12 22 33
        }
        int[] res = mergeSort(arr,0,arr.length - 1);
        StringBuffer sb = new StringBuffer();
        for (int i : res) {
            sb.append(i + " ");
        }
        System.out.print(sb);
    }

    public static int[] mergeSort(int[] arr, int low, int high){
        if (arr == null || arr.length == 0) return null;
        if(low == high) return arr;  //>= 也可
        int mid = low + (high - low) / 2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
        return arr;
    }

    public static void merge(int[] arr, int low, int mid, int high){
        int[] helper = new int[arr.length];
        for (int i = low; i <= high; i++){
            helper[i] = arr[i];
        }
        int LS = low;
        int RS = mid + 1;
        int curr = low;
        while(LS <= mid && RS <= high){
            if(helper[LS] <= helper[RS]){
                arr[curr++] = helper[LS++];
            }else{
                arr[curr++] = helper[RS++];
            }
        }
        while(LS <= mid){
            arr[curr++] = helper[LS++];
        }
    }
}
