package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see 获取长度相等的两个有序数组的上中位数难度1
 */
public class 长度不相等的有序数组中的第k小的数难度2 {
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
        //System.out.print(getKthMinNum(arr1, arr2, K));
        System.out.print(getKth(arr1, 0, N - 1, arr2, 0, M - 1, K));
    }

    /**
     * 时间复杂度O(log(m + n)), 空间复杂度O(1), 比getKthMinNum 易于理解。
     * @leetcode https://leetcode.cn/problems/median-of-two-sorted-arrays/solutions/8999/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
     * 由于数列是有序的，其实我们完全可以一半儿一半儿的排除。假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数
     * @return
     */
    public static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        // 数组为空时, start = 0, end = -1, len = 0
        if (len1 == 0) return nums2[start2 + k - 1];
        // ！！！没有这段代码会引发50行的数组越界异常
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    @Deprecated
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
            return getLeftMedian(shorts, 0, K - 1, longs, 0, K - 1);
        } else if (K > l) {
            if (shorts[K -  l - 1] >= longs[l - 1]) {
                return shorts[K - l - 1];
            }
            if (longs[K - s - 1] >= shorts[s - 1]) {
                return longs[K - s - 1];
            }
            return getLeftMedian(shorts, K - l, s - 1, longs, K - s, l - 1);
        } else {
            if (longs[K - s - 1] >= shorts[s - 1]) {
                return longs[K - s - 1];
            }
            return getLeftMedian(shorts, 0, s - 1, longs, K - s, K - 1);
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
    public static int getLeftMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
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
