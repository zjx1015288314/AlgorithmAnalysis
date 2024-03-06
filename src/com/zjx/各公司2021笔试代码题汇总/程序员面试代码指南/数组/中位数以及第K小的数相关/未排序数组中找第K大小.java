package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 在未排序的数组中找到第 k大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 */
public class 未排序数组中找第K大小 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        int len = Integer.parseInt(ss[0]);
        int k = Integer.parseInt(ss[1]);
        int[] arr = new int[len];
        ss = br.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(ss[i]);
        }
        //7 4
        //5 2 4 1 3 6 0
        System.out.println(findKthLargest(arr, k));
    }

    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) return 0;
        int len = nums.length;
        int target = len - k;  //!!!第k大就是第 len -k + 1小，索引是len - k
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int index = partition(nums, left, right);
            if (index - 1 < target) {
                //target -= index - left;  //注意target变化
                left = index;
            } else {
                right = index - 1;
            }
        }
        return nums[left];
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(arr, left++, right--);
            }
        }
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
