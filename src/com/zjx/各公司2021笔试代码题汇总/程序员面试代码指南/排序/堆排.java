package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author zhaojiexiong
 * @create 2020/7/10
 * @since 1.0.0
 */
public class 堆排 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] arr = new int[len];
        String[] ss = br.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(ss[i]);  //2 7 8 3 1 6 9 0 5 4 9 19 12 16 14 12 22 33
        }
        int[] res = heapSort(arr,len);
        StringBuffer sb = new StringBuffer();
        for (int i : res) {
            sb.append(i + " ");
        }
        System.out.print(sb);
    }

    public static int[] heapSort(int[] arr, int heapSize){
        buildHeap(arr,heapSize);
        while (heapSize > 1){
            swap(arr,0, --heapSize);
            heapify(arr,0,heapSize);
        }
        return arr;
    }
    private static void buildHeap(int[] arr, int size){
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(arr,i,size);
        }
    }
    private static void heapify(int[] arr, int i, int size){
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int max = i;
        if(left < size && arr[left] > arr[max]){
            max = left;
        }
        if(right < size && arr[right] > arr[max]){
            max = right;
        }
        if(max != i){
            swap(arr,max,i);
            heapify(arr, max, size);
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
