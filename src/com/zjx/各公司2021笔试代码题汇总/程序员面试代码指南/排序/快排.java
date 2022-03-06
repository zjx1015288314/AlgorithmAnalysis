package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/7/10
 * @since 1.0.0
 */
public class 快排 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] arr = new int[len];
        String[] ss = br.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(ss[i]);  //2 7 8 3 1 6 9 0 5 4 9 19 12 16 14 12 22 33
        }
        int[] res = quickSort(arr,0,arr.length - 1);
        StringBuffer sb = new StringBuffer();
        for (int i : res) {
            sb.append(i + " ");
        }
        System.out.print(sb);
    }

    public static int[] quickSort(int[] arr, int low, int high){
        if (arr == null || arr.length == 0) return arr;
        int index = partition(arr, low, high);
        if (low < index - 1) quickSort(arr,low,index - 1);
        if (index < high) quickSort(arr,index,high);
        return arr;
    }
    public static int partition(int[] arr, int low, int high){
        int pivot = arr[(low + high) / 2];
        while (low <= high){
            while(arr[low] < pivot) low++;
            while (arr[high] > pivot) high--;
            if(low <= high) swap(arr,low++,high--);  //in case of low > high
        }
        return low;
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
