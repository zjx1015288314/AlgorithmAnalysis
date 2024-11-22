package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 上下中位数的思路在于二分法，每次去掉不可能的一半，最后剩下的两个数中的较大值就是上中位数
 * 1. 如果两个数组长度是偶数:
 *  [1, 4, 5, 7]与[2, 3, 4, 5]比较, 当中位数比较5, 4比较时, 5比4大, 那么5以及5之后的数都不可能是下中位数(5前面有1,4,2,3,4,超出下中位数),
 *  4之前的数(注意4可能是下中位数)都不可能是下中位数, 所以第一次比较完后, 剩下的数是[1, 4]与[4, 5]比较
 * 2. 如果两个数组长度是奇数:
 *  [1, 4, 5]与[2, 3, 4]比较, 当中位数比较4, 3比较时, 4比3大, 4(注意4可能是)之后的数都不可能是上中位数,
 *  3及3之前的数都不可能是上中位数,所以第一次比较完后, 剩下的数是[1, 4]与[2, 3]比较
 *  总结一下就是，偶数长度的数组比较时, 舍弃策略更加严格, 舍弃的数更多, 奇数长度的数组比较时, 舍弃策略更加宽松, 舍弃的数更少
 * 该题与下面题型类似
 * @see 获取长度相等的两个有序数组的上中位数难度1
 *
 */
public class 获取长度相等的两个有序数组的下中位数难度1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        String[] ss = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr1[i] = Integer.parseInt(ss[i]);
        }
        ss = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr2[i] = Integer.parseInt(ss[i]);
        }
        int rightMedian = getRightMedian(arr1, arr2);
        System.out.println(rightMedian);
    }

    public static int getRightMedian(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            throw new RuntimeException("Input arr is invalid!");
        }
        int start1 = 0;
        int end1 = arr1.length - 1;
        int start2 = 0;
        int end2 = arr2.length - 1;
        while (start1 < end1) {
            int mid1 = (start1 + end1 + 1) / 2;
            int mid2 = (start2 + end2 + 1) / 2;
            int offset = ((end1 - start1 + 1) & 1) ^ 1;
            if (arr1[mid1] > arr2[mid2]) {
                end1 = mid1 - offset;
                start2 = mid2;
            } else if (arr1[mid1] < arr2[mid2]) {
                start1 = mid1;
                end2 = mid2 - offset;
            } else {
                return arr1[mid1];
            }
        }
        return Math.max(arr1[start1], arr2[start2]);
    }
}