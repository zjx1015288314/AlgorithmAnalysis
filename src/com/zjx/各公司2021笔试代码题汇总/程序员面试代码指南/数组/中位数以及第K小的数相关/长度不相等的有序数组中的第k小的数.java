package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 长度不相等的有序数组中的第k小的数 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        int N = Integer.parseInt(ss[0]);
        int M = Integer.parseInt(ss[1]);
        int K = Integer.parseInt(ss[2]);
        ss = br.readLine().split(" ");
        int[] arr1 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(ss[i]);
        }
        ss = br.readLine().split(" ");
        int[] arr2 = new int[M];
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(ss[i]);
        }
        System.out.print(getKthMinNum(arr1, arr2, K));
    }

    public static int getKthMinNum(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null || K < 1 || K > arr1.length + arr2.length) {
            throw new RuntimeException("Input data is invalid!");
        }
        //防止有数组为空造成空指针异常
        if (arr1.length == 0 || arr2.length == 0) {
            return  arr1.length == 0 ? arr2[K - 1] : arr1[K - 1];
        }

        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if (K <= s) {
            return getLeftMedian3(shorts, 0, K - 1, longs, 0, K - 1);
        } else if (K > l) {
            if (shorts[K - l - 1] >= longs[l - 1]) {
                return shorts[K - l - 1];
            }
            if (longs[K - s - 1] >= shorts[s - 1]) {
                return longs[K - s - 1];
            }
            return getLeftMedian3(shorts, K - l, s - 1, longs, K - s, l - 1);
        } else {
            if (longs[K - s - 1] >= shorts[s - 1]) {
                return longs[K - s - 1];
            }
            return getLeftMedian3(shorts, 0, s - 1, longs, K - s, K - 1);
        }
    }

    /**
     * 找到两数组的上中位数，利用数组的有序性，联想到二分法 O(logN)
     * @param arr1
     * @param s1   开始
     * @param e1   结束
     * @param arr2
     * @param s2   开始
     * @param e2   结束
     * @return
     */
    public static int getLeftMedian3(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1 - s1 + 1) & 1) ^ 1;   //个数为偶数时加1，奇数不变
            if (arr1[mid1] > arr2[mid2]) {
                e1 = mid1;
                s2 = mid2 + offset;
            } else if (arr1[mid1] < arr2[mid2]) {
                s1 = mid1 + offset;
                e2 = mid2;
            } else {
                return arr1[mid1];
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }
}
